package org.jimmyoak.workshop.optional.exercise;

import org.jimmyoak.workshop.utils.Info;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@Info({
    "Basically, make the tests pass!!",
    "DON'T TOUCH THE EXISTING CODE",
    "Ignore the implementation of the private methods. Imagine they could return null values unless they already return Optional",
})
public class Exercise3 {
  @Test
  @Info({
      "Get the first letter of possiblyAString() method, ",
      "only if the string length is larger than 4 and ends with 'y', otherwise, get unknown",
  })
  public void first_letter_with_some_conditions() throws Exception {
    String firstLetter = possiblyAString()
        .filter(string -> string.length() > 4)
        .filter(string -> string.endsWith("y"))
        .map(string -> string.charAt(0))
        .map(String::valueOf)
        .orElse("unknown");

    assertThat(firstLetter).isEqualTo("j");
  }

  @Test
  @Info({
      "Get the first letter of possiblyAString() method, ",
      "only if the string length is larger than 5 and ends with 'my', otherwise, get unknown",
  })
  public void first_letter_with_some_other_conditions() throws Exception {
    String firstLetter = possiblyAString()
        .filter(string -> string.length() > 5)
        .filter(string -> string.endsWith("y"))
        .map(string -> string.charAt(0))
        .map(String::valueOf)
        .orElse("unknown");

    assertThat(firstLetter).isEqualTo("unknown");
  }

  @Test
  @Info({
      "Get the integer value of intFrom() method, ",
      "using possiblyAString() value, or get the value from heavyOperation() instead",
  })
  public void get_int_from_possibly_a_string() throws Exception {
    Integer intValue = anotherPossibleString()
        .flatMap(this::intFrom)
        .orElseGet(this::heavyOperation);

    assertThat(intValue).isEqualTo(0);
  }

  @Test
  @Info({
      "Get intFrom('dog') value or throw IllegalArgumentException"
  })
  public void int_from_dog() throws Exception {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> intFrom("dog").orElseThrow(IllegalArgumentException::new));
  }

  private Optional<String> possiblyAString() {
    return Optional.of("jimmy");
  }

  private Optional<String> anotherPossibleString() {
    return Optional.of("asdfasdf");
  }

  private Optional<Integer> intFrom(String string) {
    return string.endsWith("my") ? Optional.of(1) : Optional.empty();
  }

  private Integer heavyOperation() {
    return 0;
  }
}
