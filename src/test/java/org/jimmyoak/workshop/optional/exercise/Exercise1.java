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
    // TODO: Write your code

    assertThat(optional).isEmpty();
  }

  @Test
  public void optionals_are_comparable() throws Exception {
    // TODO: Write your code

    assertThat(Optional.of("Jimmy").equals(optional)).isTrue();
  }

  @Test
  public void optional_has_string_representation() throws Exception {
    // TODO: Write your code

    assertThat(optional.toString()).isEqualTo("Optional.empty");
    assertThat(anotherOptional.toString()).isEqualTo("Optional[hola]");
  }
}
