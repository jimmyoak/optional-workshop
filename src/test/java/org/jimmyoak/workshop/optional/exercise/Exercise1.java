package org.jimmyoak.workshop.optional.exercise;

import org.jimmyoak.workshop.utils.Info;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Info({
    "Basically, make the tests pass!!",
    "DON'T TOUCH THE EXISTING CODE",
})
public class Exercise1 {
  @Test
  public void empty_optional() throws Exception {
    Optional<Object> optional = Optional.empty();

    assertThat(optional).isEmpty();
  }

  @Test
  public void optionals_are_comparable() throws Exception {
    Optional<String> optional = Optional.of("Jimmy");

    assertThat(Optional.of("Jimmy").equals(optional)).isTrue();
  }

  @Test
  public void optional_has_string_representation() throws Exception {
    Optional<Object> optional = Optional.empty();
    Optional<String> anotherOptional = Optional.of("hola");

    assertThat(optional.toString()).isEqualTo("Optional.empty");
    assertThat(anotherOptional.toString()).isEqualTo("Optional[hola]");
  }
}
