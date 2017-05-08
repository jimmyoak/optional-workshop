package org.jimmyoak.workshop.optional.explain.e3;

import org.jimmyoak.workshop.utils.ExerciseWhenFinished;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExerciseWhenFinished("Exercise1")
public class StringRepresentation {
  @Test
  public void when_empty() throws Exception {
    Optional<Object> empty = Optional.empty();

    assertThat(empty).hasToString("Optional.empty");
  }

  @Test
  public void when_value() throws Exception {
    Optional<Integer> integerOptional = Optional.of(69);
    Optional<String> stringOptional = Optional.of("Hello");
    Optional<ObjectWithToStringImplementation> objectOptional = Optional.of(new ObjectWithToStringImplementation());


    assertThat(integerOptional).hasToString("Optional[69]");
    assertThat(stringOptional).hasToString("Optional[Hello]");
    assertThat(objectOptional).hasToString("Optional[Hello I'm the object with a toString implementation]");
  }

  private static class ObjectWithToStringImplementation {
    @Override
    public String toString() {
      return "Hello I'm the object with a toString implementation";
    }
  }
}
