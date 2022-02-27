package conrad.codeworkshop.core.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Var;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/**
 * Immutable implementation of {@link SearchQualityResponse}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code ImmutableSearchQualityResponse.builder()}.
 */
@Generated(from = "SearchQualityResponse", generator = "Immutables")
@SuppressWarnings({"all"})
@ParametersAreNonnullByDefault
@javax.annotation.Generated("org.immutables.processor.ProxyProcessor")
@Immutable
@CheckReturnValue
public final class ImmutableSearchQualityResponse
    implements SearchQualityResponse {
  private final ImmutableList<SearchHit> currentHits;
  private final ImmutableList<SearchHit> idealHits;
  private final ImmutableMap<String, List<Integer>> compareMap;

  private ImmutableSearchQualityResponse(
      ImmutableList<SearchHit> currentHits,
      ImmutableList<SearchHit> idealHits,
      ImmutableMap<String, List<Integer>> compareMap) {
    this.currentHits = currentHits;
    this.idealHits = idealHits;
    this.compareMap = compareMap;
  }

  /**
   * @return The value of the {@code currentHits} attribute
   */
  @JsonProperty("currentHits")
  @Override
  public ImmutableList<SearchHit> currentHits() {
    return currentHits;
  }

  /**
   * @return The value of the {@code idealHits} attribute
   */
  @JsonProperty("idealHits")
  @Override
  public ImmutableList<SearchHit> idealHits() {
    return idealHits;
  }

  /**
   * @return The value of the {@code compareMap} attribute
   */
  @JsonProperty("compareMap")
  @Override
  public ImmutableMap<String, List<Integer>> compareMap() {
    return compareMap;
  }

  /**
   * Copy the current immutable object with elements that replace the content of {@link SearchQualityResponse#currentHits() currentHits}.
   * @param elements The elements to set
   * @return A modified copy of {@code this} object
   */
  public final ImmutableSearchQualityResponse withCurrentHits(SearchHit... elements) {
    ImmutableList<SearchHit> newValue = ImmutableList.copyOf(elements);
    return new ImmutableSearchQualityResponse(newValue, this.idealHits, this.compareMap);
  }

  /**
   * Copy the current immutable object with elements that replace the content of {@link SearchQualityResponse#currentHits() currentHits}.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param elements An iterable of currentHits elements to set
   * @return A modified copy of {@code this} object
   */
  public final ImmutableSearchQualityResponse withCurrentHits(Iterable<? extends SearchHit> elements) {
    if (this.currentHits == elements) return this;
    ImmutableList<SearchHit> newValue = ImmutableList.copyOf(elements);
    return new ImmutableSearchQualityResponse(newValue, this.idealHits, this.compareMap);
  }

  /**
   * Copy the current immutable object with elements that replace the content of {@link SearchQualityResponse#idealHits() idealHits}.
   * @param elements The elements to set
   * @return A modified copy of {@code this} object
   */
  public final ImmutableSearchQualityResponse withIdealHits(SearchHit... elements) {
    ImmutableList<SearchHit> newValue = ImmutableList.copyOf(elements);
    return new ImmutableSearchQualityResponse(this.currentHits, newValue, this.compareMap);
  }

  /**
   * Copy the current immutable object with elements that replace the content of {@link SearchQualityResponse#idealHits() idealHits}.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param elements An iterable of idealHits elements to set
   * @return A modified copy of {@code this} object
   */
  public final ImmutableSearchQualityResponse withIdealHits(Iterable<? extends SearchHit> elements) {
    if (this.idealHits == elements) return this;
    ImmutableList<SearchHit> newValue = ImmutableList.copyOf(elements);
    return new ImmutableSearchQualityResponse(this.currentHits, newValue, this.compareMap);
  }

  /**
   * Copy the current immutable object by replacing the {@link SearchQualityResponse#compareMap() compareMap} map with the specified map.
   * Nulls are not permitted as keys or values.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param entries The entries to be added to the compareMap map
   * @return A modified copy of {@code this} object
   */
  public final ImmutableSearchQualityResponse withCompareMap(Map<String, ? extends List<Integer>> entries) {
    if (this.compareMap == entries) return this;
    ImmutableMap<String, List<Integer>> newValue = ImmutableMap.copyOf(entries);
    return new ImmutableSearchQualityResponse(this.currentHits, this.idealHits, newValue);
  }

  /**
   * This instance is equal to all instances of {@code ImmutableSearchQualityResponse} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(@Nullable Object another) {
    if (this == another) return true;
    return another instanceof ImmutableSearchQualityResponse
        && equalTo((ImmutableSearchQualityResponse) another);
  }

  private boolean equalTo(ImmutableSearchQualityResponse another) {
    return currentHits.equals(another.currentHits)
        && idealHits.equals(another.idealHits)
        && compareMap.equals(another.compareMap);
  }

  /**
   * Computes a hash code from attributes: {@code currentHits}, {@code idealHits}, {@code compareMap}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    @Var int h = 5381;
    h += (h << 5) + currentHits.hashCode();
    h += (h << 5) + idealHits.hashCode();
    h += (h << 5) + compareMap.hashCode();
    return h;
  }

  /**
   * Prints the immutable value {@code SearchQualityResponse} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return MoreObjects.toStringHelper("SearchQualityResponse")
        .omitNullValues()
        .add("currentHits", currentHits)
        .add("idealHits", idealHits)
        .add("compareMap", compareMap)
        .toString();
  }

  /**
   * Creates an immutable copy of a {@link SearchQualityResponse} value.
   * Uses accessors to get values to initialize the new immutable instance.
   * If an instance is already immutable, it is returned as is.
   * @param instance The instance to copy
   * @return A copied immutable SearchQualityResponse instance
   */
  public static SearchQualityResponse copyOf(SearchQualityResponse instance) {
    if (instance instanceof ImmutableSearchQualityResponse) {
      return (ImmutableSearchQualityResponse) instance;
    }
    return ImmutableSearchQualityResponse.builder()
        .from(instance)
        .build();
  }

  /**
   * Creates a builder for {@link SearchQualityResponse SearchQualityResponse}.
   * <pre>
   * ImmutableSearchQualityResponse.builder()
   *    .addCurrentHit|addAllCurrentHits(conrad.codeworkshop.core.api.SearchHit) // {@link SearchQualityResponse#currentHits() currentHits} elements
   *    .addIdealHit|addAllIdealHits(conrad.codeworkshop.core.api.SearchHit) // {@link SearchQualityResponse#idealHits() idealHits} elements
   *    .putCompareMap|putAllCompareMap(String =&gt; List&amp;lt;Integer&amp;gt;) // {@link SearchQualityResponse#compareMap() compareMap} mappings
   *    .build();
   * </pre>
   * @return A new SearchQualityResponse builder
   */
  public static ImmutableSearchQualityResponse.Builder builder() {
    return new ImmutableSearchQualityResponse.Builder();
  }

  /**
   * Builds instances of type {@link SearchQualityResponse SearchQualityResponse}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "SearchQualityResponse", generator = "Immutables")
  @NotThreadSafe
  public static final class Builder {
    private ImmutableList.Builder<SearchHit> currentHits = ImmutableList.builder();
    private ImmutableList.Builder<SearchHit> idealHits = ImmutableList.builder();
    private ImmutableMap.Builder<String, List<Integer>> compareMap = ImmutableMap.builder();

    private Builder() {
    }

    /**
     * Fill a builder with attribute values from the provided {@code SearchQualityResponse} instance.
     * Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     * Collection elements and entries will be added, not replaced.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder from(SearchQualityResponse instance) {
      Objects.requireNonNull(instance, "instance");
      addAllCurrentHits(instance.currentHits());
      addAllIdealHits(instance.idealHits());
      putAllCompareMap(instance.compareMap());
      return this;
    }

    /**
     * Adds one element to {@link SearchQualityResponse#currentHits() currentHits} list.
     * @param element A currentHits element
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder addCurrentHit(SearchHit element) {
      element = ImmutableSearchHit.copyOf(element);
      this.currentHits.add(element);
      return this;
    }

    /**
     * Adds elements to {@link SearchQualityResponse#currentHits() currentHits} list.
     * @param elements An array of currentHits elements
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder addCurrentHits(SearchHit... elements) {
      for (SearchHit element : elements) {
        element = ImmutableSearchHit.copyOf(element);
        this.currentHits.add(Objects.requireNonNull(element, "currentHits element"));
      }
      return this;
    }


    /**
     * Sets or replaces all elements for {@link SearchQualityResponse#currentHits() currentHits} list.
     * @param elements An iterable of currentHits elements
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    @JsonProperty("currentHits")
    public final Builder currentHits(Iterable<? extends SearchHit> elements) {
      this.currentHits = ImmutableList.builder();
      return addAllCurrentHits(elements);
    }

    /**
     * Adds elements to {@link SearchQualityResponse#currentHits() currentHits} list.
     * @param elements An iterable of currentHits elements
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder addAllCurrentHits(Iterable<? extends SearchHit> elements) {
      for (SearchHit element : elements) {
        element = ImmutableSearchHit.copyOf(element);
        this.currentHits.add(Objects.requireNonNull(element, "currentHits element"));
      }
      return this;
    }

    /**
     * Adds one element to {@link SearchQualityResponse#idealHits() idealHits} list.
     * @param element A idealHits element
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder addIdealHit(SearchHit element) {
      element = ImmutableSearchHit.copyOf(element);
      this.idealHits.add(element);
      return this;
    }

    /**
     * Adds elements to {@link SearchQualityResponse#idealHits() idealHits} list.
     * @param elements An array of idealHits elements
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder addIdealHits(SearchHit... elements) {
      for (SearchHit element : elements) {
        element = ImmutableSearchHit.copyOf(element);
        this.idealHits.add(Objects.requireNonNull(element, "idealHits element"));
      }
      return this;
    }


    /**
     * Sets or replaces all elements for {@link SearchQualityResponse#idealHits() idealHits} list.
     * @param elements An iterable of idealHits elements
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    @JsonProperty("idealHits")
    public final Builder idealHits(Iterable<? extends SearchHit> elements) {
      this.idealHits = ImmutableList.builder();
      return addAllIdealHits(elements);
    }

    /**
     * Adds elements to {@link SearchQualityResponse#idealHits() idealHits} list.
     * @param elements An iterable of idealHits elements
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder addAllIdealHits(Iterable<? extends SearchHit> elements) {
      for (SearchHit element : elements) {
        element = ImmutableSearchHit.copyOf(element);
        this.idealHits.add(Objects.requireNonNull(element, "idealHits element"));
      }
      return this;
    }

    /**
     * Put one entry to the {@link SearchQualityResponse#compareMap() compareMap} map.
     * @param key The key in the compareMap map
     * @param value The associated value in the compareMap map
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder putCompareMap(String key, List<Integer> value) {
      this.compareMap.put(key, value);
      return this;
    }

    /**
     * Put one entry to the {@link SearchQualityResponse#compareMap() compareMap} map. Nulls are not permitted
     * @param entry The key and value entry
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder putCompareMap(Map.Entry<String, ? extends List<Integer>> entry) {
      this.compareMap.put(entry);
      return this;
    }

    /**
     * Sets or replaces all mappings from the specified map as entries for the {@link SearchQualityResponse#compareMap() compareMap} map. Nulls are not permitted
     * @param entries The entries that will be added to the compareMap map
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    @JsonProperty("compareMap")
    public final Builder compareMap(Map<String, ? extends List<Integer>> entries) {
      this.compareMap = ImmutableMap.builder();
      return putAllCompareMap(entries);
    }

    /**
     * Put all mappings from the specified map as entries to {@link SearchQualityResponse#compareMap() compareMap} map. Nulls are not permitted
     * @param entries The entries that will be added to the compareMap map
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder putAllCompareMap(Map<String, ? extends List<Integer>> entries) {
      this.compareMap.putAll(entries);
      return this;
    }

    /**
     * Builds a new {@link SearchQualityResponse SearchQualityResponse}.
     * @return An immutable instance of SearchQualityResponse
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public SearchQualityResponse build() {
      return new ImmutableSearchQualityResponse(currentHits.build(), idealHits.build(), compareMap.build());
    }
  }
}
