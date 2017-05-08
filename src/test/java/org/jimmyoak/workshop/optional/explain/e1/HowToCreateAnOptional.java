package org.jimmyoak.workshop.optional.explain.e1;

import org.jimmyoak.workshop.utils.Info;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@Info("Optional encapsulates null as non-value. Returning an optional makes caller treat the non-value case")
public class HowToCreateAnOptional {
  @Test
  public void create_without_value() throws Exception {
    Optional<Object> optional = Optional.empty();

    assertThat(optional).isEmpty();
  }

  @Test
  public void with_value() throws Exception {
    Optional<Integer> optional = Optional.of(5);

    assertThat(optional).contains(5);
  }

  @Test
  public void try_creating_with_value_but_passing_null() throws Exception {
    assertThatExceptionOfType(NullPointerException.class)
        .isThrownBy(() -> Optional.of(null));
  }

  @Test
  public void creating_when_you_dont_know_if_value_will_be_present() throws Exception {
    Optional<Integer> optional = Optional.ofNullable(foo());

    assertThat(optional).isEmpty();
  }

  private Integer foo() {
    return null;
  }
}
