package org.jimmyoak.workshop.optional.explain.e5;

import org.jimmyoak.workshop.utils.Info;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@RunWith(MockitoJUnitRunner.class)
public class GoodUsage {

  @Test
  @Info({
      "Gets value or specified one otherwise",
      "Why not getOrElse? Just read it. It's more semantic this way",
  })
  public void or_else() throws Exception {
    Optional<Integer> limitFromConfig = Optional.of(3);
    Optional<String> username = Optional.empty();

    assertThat(limitFromConfig.orElse(5)).isEqualTo(3);
    assertThat(username.orElse("Anonymous")).isEqualTo("Anonymous");
  }

  @Test
  @Info({
      "Gets value or result value from passing closure",
      "Wait, why the **** would I do that?",
      "Well, closure is not executed unless there's no present value. This means, in case you don't have the value",
      "you want, you can call a repository for example",
  })
  public void or_else_get() throws Exception {
    Optional<Integer> limitFromConfig = Optional.of(3);
    Optional<String> username = Optional.empty();

    assertThat(limitFromConfig.orElseGet(this::defaultLimit)).isEqualTo(3);
    assertThat(username.orElseGet(this::defaultUserName)).isEqualTo("Anonymous");
  }

  @Test
  @Info({
      "Gets value or throws exception given by closure",
      "Why?",
      "You can throw any exception you want, instead of a NoSuchElementException like .get does",
  })
  public void or_else_throw() throws Exception {
    Optional<Integer> number = Optional.of(5);
    Optional<Integer> empty = Optional.empty();

    assertThat(number.orElseThrow(IllegalArgumentException::new)).isEqualTo(5);
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> empty.orElseThrow(IllegalArgumentException::new));
  }

  private Integer defaultLimit() {
    return 5;
  }

  private String defaultUserName() {
    return "Anonymous";
  }
}
