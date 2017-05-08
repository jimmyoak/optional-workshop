package org.jimmyoak.workshop.optional.explain.e6;

import org.jimmyoak.workshop.utils.Info;
import org.junit.Test;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@Info({
    "Optional provides three methods to operate over the present value",
    "Callback will execute when a value is present",
})
public class Operators {

  @Test
  @Info("Value can be filtered and turn the optional into an empty one")
  public void filter() throws Exception {
    Integer ten = Optional.of(10)
        .filter(this::isDivisibleBy5)
        .orElse(0);

    Integer zero = Optional.of(11)
        .filter(this::isDivisibleBy5)
        .orElse(0);

    assertThat(ten).isEqualTo(10);
    assertThat(zero).isEqualTo(0);
  }

  @Test
  @Info("Value can be converted")
  public void map() throws Exception {
    String string = Optional.of(10)
        .map(this::generateStringOfAsWithLength)
        .orElse("");

    String emptyString = Optional.<Integer>empty()
        .map(this::generateStringOfAsWithLength)
        .orElse("");

    assertThat(string).hasSize(10);
    assertThat(emptyString).hasSize(0);
  }

  @Test
  @Info("Optional can be converted to another optional")
  public void flatMap() throws Exception {
    String string = Optional.of(10)
        .flatMap(this::generateStringOfAsIfDivisibleBy5)
        .orElse("");

    String emptyString = Optional.of(7)
        .flatMap(this::generateStringOfAsIfDivisibleBy5)
        .orElse("");

    assertThat(string).hasSize(10);
    assertThat(emptyString).hasSize(0);
  }

  private boolean isDivisibleBy5(Integer number) {
    return number % 5 == 0;
  }

  private String generateStringOfAsWithLength(int length) {
    return IntStream.range(0, length)
        .mapToObj(it -> "a")
        .collect(Collectors.joining());
  }

  private Optional<String> generateStringOfAsIfDivisibleBy5(Integer length) {
    return Optional.of(length)
        .filter(this::isDivisibleBy5)
        .map(this::generateStringOfAsWithLength);
  }

}
