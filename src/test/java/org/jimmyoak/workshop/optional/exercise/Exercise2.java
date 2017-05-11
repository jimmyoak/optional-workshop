package org.jimmyoak.workshop.optional.exercise;

import org.jimmyoak.workshop.utils.Info;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

@Info({
    "Basically, make the tests pass!!",
    "DON'T TOUCH THE EXISTING CODE",
})
@RunWith(MockitoJUnitRunner.class)
public class Exercise2 {
  @Mock
  private Repository repository;

  @Test
  @Info("Must call repository.find() if optional contains a value")
  public void if_value_is_present() throws Exception {
    // TODO: Write your code

//    verify(repository).findBy(5);
  }

  @Test
  @Info("Must call repository.find() if optional does NOT contain a value")
  public void if_value_is_not_present() throws Exception {
    // TODO: Write your code

//    verify(repository).findAny();
  }

  @Test
  public void get_optional_value() throws Exception {
    // TODO: Write your code

//    assertThat(optional.get()).isEqualTo(3);
  }

  private interface Repository {
    Optional<String> findAny();

    Optional<String> findBy(int id);
  }
}
