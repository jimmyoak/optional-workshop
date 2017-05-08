package org.jimmyoak.workshop.optional.explain.e7;

import org.jimmyoak.workshop.utils.AvoidUsage;
import org.jimmyoak.workshop.utils.Info;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class)
@Info("Let's talk a bit about Optional internals")
public class InDepth {
  @Mock
  private Sequence sequence;

  @Test
  @Info("Optional creates new instances each time we call a method")
  public void optional_is_immutable() throws Exception {
    Optional<Integer> optional = Optional.of(5);
    Optional<Integer> mapped = optional.map(it -> 3);

    assertThat(optional).isNotSameAs(mapped);
  }

  @Test
  @Info("Optional executes each method without waiting for a finisher (orElse, ifPresent,...)")
  public void optional_executes_each_method_without_waiting_for_a_finisher() throws Exception {
    InOrder inOrder = inOrder(sequence);

    Optional<Integer> optional = Optional.of(5);

    sequence.on(0);

    Optional<Integer> mapped = optional.map(it -> {
      sequence.on("map");
      return 3;
    });

    sequence.on(1);

    Optional<Integer> filtered = mapped.filter(it -> {
      sequence.on("filter");
      return false;
    });

    sequence.on(2);

    Integer value = filtered.orElseGet(() -> {
      sequence.on("orElseGet");
      return 0;
    });

    inOrder.verify(sequence).on(0);
    inOrder.verify(sequence).on("map");
    inOrder.verify(sequence).on(1);
    inOrder.verify(sequence).on("filter");
    inOrder.verify(sequence).on(2);
    inOrder.verify(sequence).on("orElseGet");

    assertThat(value).isEqualTo(0);
  }

  @Test
  @Info("Remember optional creates new instances")
  @AvoidUsage
  public void remember_optional_creates_new_instances() throws Exception {
    Optional<Integer> optional = Optional.of(5);
    optional.map(it -> 3);
    optional.filter(it -> false);
    Integer value = optional.orElse(0);

    assertThat(value).isEqualTo(5);
  }

  @Test
  @Info("Optional is optimized for empty cases")
  public void optional_empty_is_same_instance() throws Exception {
    Optional<Object> empty = Optional.empty();
    Optional<Object> anotherEmpty = Optional.empty();

    assertThat(empty).isSameAs(anotherEmpty);
  }

  @Test
  @Info("Optional empty is same instance including when its mapped to empty or filtered")
  public void optional_empty_is_same_instance_including_when_its_mapped_to_empty_or_filtered() throws Exception {
    Optional<Object> empty = Optional.empty();
    Optional<Integer> integerOptional = Optional.of(5);
    Optional<Object> mappedToEmpty = integerOptional.map(it -> null);

    assertThat(empty).isSameAs(mappedToEmpty);
    assertThat(integerOptional).isNotSameAs(mappedToEmpty);
  }

  private interface Sequence {
    void on(Object position);
  }
}
