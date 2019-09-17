import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.Test;
import util.CommonUtils;


public class CommonUtilsTest {

  @Test
  public void testTopN1() {
    List<Integer> list = Arrays
        .asList(99_999, 99_998, 99_997, 99_996, 99_995, 99_994, 99_993, 99_992, 99_991, 99_990);
    Stream<Integer> integerStream = Stream.iterate(0, value -> value + 1).limit(100_000);
    List<Integer> integers = CommonUtils.topN(integerStream);
    assertThat(list, is(integers));
  }

  @Test
  public void testTopN2() {
    List<Integer> list = Arrays
        .asList(99_999_999, 99_999_998, 99_999_997, 99_999_996, 99_999_995, 99_999_994, 99_999_993, 99_999_992,
            99_999_991, 99_999_990);
    List<Integer> integers = CommonUtils.topN(Stream.iterate(0, value -> value + 1).limit(100_000_000));
    assertThat(list, is(integers));
  }

  @Test
  public void testNullTopN() {
    Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, null);
    List<Integer> list = Arrays.asList(5, 4, 3, 2, 1);
    List<Integer> integers = CommonUtils.topN(integerStream);
    assertThat(list, is(integers));
  }

  @Test
  public void testNotUniqueTopN() {
    Stream<Integer> integerStream = Stream.of(5, 5, 5, 5, 5, 10, 10, 10, 10, 10);
    List<Integer> list = Arrays.asList(10, 10, 10, 10, 10, 5, 5, 5, 5, 5);
    List<Integer> integers = CommonUtils.topN(integerStream);
    assertThat(list, is(integers));
  }
}
