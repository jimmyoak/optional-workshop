package org.jimmyoak.workshop.optional.explain.e2;

import org.jimmyoak.workshop.utils.InfoUrl;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class OptionalComparability {
  @Test
  public void is_comparable_when_empty() throws Exception {
    Optional<Object> empty = Optional.empty();
    Optional<Integer> anotherEmpty = Optional.ofNullable(null);
    Optional<Integer> withValue = Optional.of(4);

    assertThat(empty.equals(anotherEmpty)).isTrue();
    assertThat(empty.equals(withValue)).isFalse();
  }

  @Test
  public void is_comparable_with_value() throws Exception {
    Optional<Integer> withValue = Optional.of(4);
    Optional<Integer> withSameValue = Optional.of(4);
    Optional<Integer> withAnotherValue = Optional.of(5);

    assertThat(withValue.equals(withSameValue)).isTrue();
    assertThat(withValue.equals(withAnotherValue)).isFalse();
  }

  @Test
  @InfoUrl("https://en.wikipedia.org/wiki/Java_hashCode()")
  public void also_the_hash_code() throws Exception {
    Optional<Object> empty = Optional.empty();
    Optional<Integer> anotherEmpty = Optional.ofNullable(null);
    Optional<Integer> withValue = Optional.of(4);
    Optional<Integer> withSameValue = Optional.of(4);
    Optional<Integer> withAnotherValue = Optional.of(5);

    assertThat(empty.hashCode()).isEqualTo(anotherEmpty.hashCode());
    assertThat(withValue.hashCode()).isEqualTo(withSameValue.hashCode());

    assertThat(withValue.hashCode()).isNotEqualTo(withAnotherValue.hashCode());
    assertThat(empty.hashCode()).isNotEqualTo(withAnotherValue.hashCode());
  }
}
