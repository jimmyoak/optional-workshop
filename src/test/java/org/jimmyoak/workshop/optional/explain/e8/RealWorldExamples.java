package org.jimmyoak.workshop.optional.explain.e8;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@RunWith(MockitoJUnitRunner.class)
public class RealWorldExamples {
  private UserRepository userRepository;

  @Before
  public void setUp() throws Exception {
    userRepository = new InMemoryUserRepository();
  }

  @Test
  public void should_get_user() throws Exception {
    User user = userRepository.findBy(1).orElseThrow(UserNotFound::new);

    assertThat(user.getUserId()).isEqualTo(1);
  }

  @Test
  public void should_throw_exception_when_user_not_found() throws Exception {
    assertThatExceptionOfType(UserNotFound.class)
        .isThrownBy(() -> userRepository.findBy(0).orElseThrow(UserNotFound::new));
  }

  private interface UserRepository {
    Optional<User> findBy(int userId);
  }

  private static class InMemoryUserRepository implements UserRepository {
    private Map<Integer, User> users = Collections.singletonMap(1, new User(1));

    public Optional<User> findBy(int userId) {
      return Optional.ofNullable(users.get(userId));
    }
  }

  private static class UserNotFound extends RuntimeException {
  }

  private static class User {
    private int userId;

    public User(int userId) {
      this.userId = userId;
    }

    public int getUserId() {
      return userId;
    }
  }
}
