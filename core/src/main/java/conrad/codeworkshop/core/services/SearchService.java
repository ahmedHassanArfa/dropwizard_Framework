package conrad.codeworkshop.core.services;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import conrad.codeworkshop.core.Config;
import conrad.codeworkshop.core.api.FieldNames;
import conrad.codeworkshop.core.api.ImmutableMovieSearchResponse;
import conrad.codeworkshop.core.api.ImmutableSearchHit;
import conrad.codeworkshop.core.api.ImmutableSearchQualityResponse;
import conrad.codeworkshop.core.api.Lang;
import conrad.codeworkshop.core.api.MovieSearchRequest;
import conrad.codeworkshop.core.api.MovieSearchResponse;
import conrad.codeworkshop.core.api.SearchHit;
import conrad.codeworkshop.core.api.SearchQualityResponse;
import conrad.codeworkshop.core.api.Sort;
import conrad.codeworkshop.core.util.HitUtil;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import javax.inject.Singleton;
import javax.ws.rs.core.MultivaluedHashMap;
import java.io.IOException;
import java.util.*;

@Singleton
public final class SearchService {

  private final Config config;
  private final RestHighLevelClient restHighLevelClient;
  private static final String INDEX = "tmdb";

  public SearchService(final Config config) {
    this.config = config;
    restHighLevelClient = new RestHighLevelClient(
        RestClient.builder(
            new HttpHost(config.getElasticsearchHost(), config.getElasticsearchPort(), "http"))
            .setDefaultHeaders(new Header[]{
                new BasicHeader("Content-Type", "application/json")
            }));
  }

  public MovieSearchResponse search(final MovieSearchRequest movieSearchRequest) throws IOException {
    if (config.isShouldDoSearch()) {
      final SearchResponse esResponse = restHighLevelClient
          .search(buildEsRequest(movieSearchRequest, Optional.empty()), RequestOptions.DEFAULT);

      return buildMovieSearchResponse(esResponse, movieSearchRequest);
    }

    return ImmutableMovieSearchResponse.of(movieSearchRequest);
  }

  public MovieSearchResponse searchByMovieLang(final MovieSearchRequest movieSearchRequest, final Lang lang) throws IOException {
    if (config.isShouldDoSearch()) {
      final SearchResponse esResponse = restHighLevelClient
          .search(buildEsRequest(movieSearchRequest, Optional.of(lang)), RequestOptions.DEFAULT);

      return buildMovieSearchResponse(esResponse, movieSearchRequest);
    }

    return ImmutableMovieSearchResponse.of(movieSearchRequest);
  }

  public SearchQualityResponse measureSearchQuality(final MovieSearchRequest movieSearchRequest) throws IOException {
    final MovieSearchResponse movieSearchResponse = search(movieSearchRequest);
    final ImmutableList<SearchHit> idealHits = getIdealSearchResultOrder(movieSearchResponse.hits());
    return ImmutableSearchQualityResponse.builder()
        .currentHits(movieSearchResponse.hits())
        .idealHits(idealHits).compareMap(measureQualityComparison(movieSearchResponse.hits(), idealHits))
        .build();
  }

  private MovieSearchResponse buildMovieSearchResponse(final SearchResponse esResponse, final MovieSearchRequest movieSearchRequest) {
    final ImmutableList.Builder<SearchHit> immutableListBuilder = ImmutableList.builder();
    esResponse.getHits().forEach(hit -> immutableListBuilder.add(ImmutableSearchHit.builder()
        .id(String.valueOf(HitUtil.get(hit.getSourceAsMap(), FieldNames.ID, "")))
        .overview(HitUtil.get(hit.getSourceAsMap(), FieldNames.OVERVIEW, ""))
        .title(HitUtil.get(hit.getSourceAsMap(), FieldNames.TITLE, ""))
        .originalTitle(HitUtil.get(hit.getSourceAsMap(), FieldNames.ORIGINAL_TITLE, ""))
        .status(HitUtil.get(hit.getSourceAsMap(), FieldNames.STATUS, ""))
        .popularity(String.valueOf(HitUtil.get(hit.getSourceAsMap(), FieldNames.POPULARITY, "")))
        .runtime(String.valueOf(HitUtil.get(hit.getSourceAsMap(), FieldNames.RUNTIME, "")))
        .voteAverage(String.valueOf(HitUtil.get(hit.getSourceAsMap(), FieldNames.VOTE_AVERAGE, "")))
        .voteCount(String.valueOf(HitUtil.get(hit.getSourceAsMap(), FieldNames.VOTE_COUNT, "")))
        .originaLanguage(HitUtil.get(hit.getSourceAsMap(), FieldNames.ORIGINAL_LANGUAGE, ""))
        .build()));

    return ImmutableMovieSearchResponse.builder()
        .request(movieSearchRequest)
        .hits(immutableListBuilder.build())
        .build();
  }


  private SearchRequest buildEsRequest(final MovieSearchRequest movieSearchRequest, final Optional<Lang> lang) {
    final SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    final MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders
        .multiMatchQuery(movieSearchRequest.query())
        .operator(Operator.AND)
        .slop(2)
        .field(FieldNames.ORIGINAL_TITLE, 20.0f)
        .field(FieldNames.TITLE, 12.5f)
        .field(FieldNames.OVERVIEW, 2.5f);
    final BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
        .must(multiMatchQueryBuilder);

    lang.ifPresent(lang1 -> boolQueryBuilder.filter(QueryBuilders.termQuery(FieldNames.ORIGINAL_LANGUAGE, lang1.toString().toLowerCase())));

    searchSourceBuilder.query(boolQueryBuilder);
    searchSourceBuilder.size(movieSearchRequest.size());
    searchSourceBuilder.from(movieSearchRequest.from());

    final Sort sort = movieSearchRequest.sort();
    if (sort != null) {
      searchSourceBuilder.sort(new FieldSortBuilder(sort.fieldName().toLowerCase()).order(SortOrder.fromString(sort.order().name())));
    }

    final SearchRequest esSearchRequest = new SearchRequest();
    esSearchRequest.indices(INDEX);
    esSearchRequest.source(searchSourceBuilder);

    return esSearchRequest;
  }


  private ImmutableList<SearchHit> getIdealSearchResultOrder(final ImmutableList<SearchHit> searchHits) {
    final List<SearchHit> hits = new ArrayList<>(searchHits);
    Collections.shuffle(hits);
    final ImmutableList.Builder<SearchHit> immutableListBuilder = ImmutableList.builder();
    return immutableListBuilder.addAll(hits).build();
  }

  private ImmutableMap<String, List<Integer>> measureQualityComparison(final ImmutableList<SearchHit> searchHits,
                                                                       final ImmutableList<SearchHit> idealHits) {
    final Map<String,List<Integer>> compareMap = new HashMap<>();
    for (int index=0; index<searchHits.size(); index++) {
        List<Integer> indexes = new ArrayList<>();
        indexes.add(index);
        compareMap.put(searchHits.get(index).id(), indexes);
    }
    for (int index=0; index<idealHits.size(); index++) {
      List<Integer> indexes = compareMap.get(idealHits.get(index).id());
      if(indexes != null) {
        indexes.add(index);
      }
    }
      return ImmutableMap.copyOf(compareMap);
  }

}
