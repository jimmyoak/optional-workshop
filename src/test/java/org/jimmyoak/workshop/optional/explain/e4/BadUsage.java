package org.jimmyoak.workshop.optional.explain.e4;

import org.jimmyoak.workshop.utils.AvoidUsage;
import org.jimmyoak.workshop.utils.ExerciseWhenFinished;
import org.jimmyoak.workshop.utils.Info;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@Info({
    "Optional allows us to treat its possible value in a different ways. Some ways are discouraged and should be avoided.",
    "Here we will see the ones to avoid its usage"
})
@ExerciseWhenFinished("Exercise2")
public class BadUsage {

  @Mock
  private Repository repository;

  @Test
  @Info("Get method allows us to get Optional present value or will throw a NoSuchElementException if no present value")
  @AvoidUsage({
      "This method would do the same as if you called a method on the nullable-variable, but throwing another kind of exception",
      "For example:",
      "Foo foo = null;",
      "foo.someMethod();",
  })
  public void get() throws Exception {
    Optional<Integer> optional = Optional.of(5);
    Optional<Integer> empty = Optional.empty();


    assertThat(optional.get()).isEqualTo(5);

    assertThatExceptionOfType(NoSuchElementException.class)
        .isThrownBy(empty::get);
  }

  @Test
  @Info("Checks whether an optional contains a value or not")
  @AvoidUsage("Dafaq? You could do that this way: foo != null")
  public void is_present() throws Exception {
    Optional<Integer> optional = Optional.of(3);
    Optional<Object> empty = Optional.empty();

    assertThat(optional.isPresent()).isTrue();
    assertThat(empty.isPresent()).isFalse();
  }

  @Test
  @Info("Executes closure in case value is present")
  @AvoidUsage("What about no-present value?")
  public void if_present() throws Exception {
    Optional<Integer> optional = Optional.of(3);
    Optional<Object> empty = Optional.empty();

    optional.ifPresent(repository::findAllWithLimit); // or: .ifPresent(limit -> repository.finAllWithLimit(limit))
    empty.ifPresent(it -> repository.findAll());

    verify(repository).findAllWithLimit(3);
    verify(repository, never()).findAll();
  }

  private interface Repository {
    void findAllWithLimit(Integer limit);

    void findAll();
  }
}
