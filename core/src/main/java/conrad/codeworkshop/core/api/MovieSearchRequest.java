package conrad.codeworkshop.core.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.dropwizard.validation.ValidationMethod;
import org.immutables.value.Value;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

@Value.Style(
    overshadowImplementation = true,
    depluralize = true,
    deepImmutablesDetection = true,
    allMandatoryParameters = true)
@JsonDeserialize(builder = ImmutableMovieSearchRequest.Builder.class)
@JsonSerialize(as = ImmutableMovieSearchRequest.class)
@Value.Immutable
public interface MovieSearchRequest {

  int from();

  int size();

  String query();

  @Nullable
  Sort sort();

  @ValidationMethod(message="Sort Field should be one of this vote_average,vote_count,runtime,popularity,id")
  @JsonIgnore
  default boolean isSortFieldName()
  {
    return Arrays.asList("vote_average","vote_count","runtime","popularity","id").
            contains(sort().fieldName());
  }

}
