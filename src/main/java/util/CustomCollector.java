package util;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Custom collector with deque accumulation type to manage collectibles.
 *
 * @author Roman M.
 */
public class CustomCollector implements
    Collector<Integer, ArrayDeque<Integer>, LinkedList<Integer>> {


  @Override
  public Supplier<ArrayDeque<Integer>> supplier() {
    return ArrayDeque::new;
  }

  @Override
  public BiConsumer<ArrayDeque<Integer>, Integer> accumulator() {
    return (integers, integer) -> {
      if (integers.size() == 10) {
        integers.removeIf(integer1 -> integer1 < integer);
        if (integers.size() <= 10) {
          integers.addFirst(integer);
        }
      } else {
        integers.addFirst(integer);
      }
    };
  }

  @Override
  public BinaryOperator<ArrayDeque<Integer>> combiner() {
    return null;
  }

  @Override
  public Function<ArrayDeque<Integer>, LinkedList<Integer>> finisher() {
    return LinkedList::new;
  }

  @Override
  public Set<Characteristics> characteristics() {
    return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED));
  }
}
