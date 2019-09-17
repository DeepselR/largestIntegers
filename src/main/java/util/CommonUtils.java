package util;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class CommonUtils {

  /**
   * Method finds the top 10 (i.e. largest 10) integer values from an input source and returns them as a list. The
   * values return in descending order.
   *
   * @param input input source
   * @return list of top 10 largest integers
   * @author Roman M.
   */
  public static List<Integer> topN(Stream<Integer> input) {
    return input.filter(Objects::nonNull)
        .mapToInt(Integer::intValue)
        .sorted()
        .boxed()
        .collect(new CustomCollector());
  }
}
