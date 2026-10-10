package problems.twosum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class TwoSumTest {

    private static final TwoSum solver = new TwoSum();

    private static final Map<String, BiFunction<int[], Integer, int[]>> VARIANTS = Map.of(
            "bruteForce", solver::bruteForce,
            "optimal", solver::optimal
    );

    private static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(new int[]{2, 7, 11, 15}, 9, new int[]{0, 1}),
                Arguments.of(new int[]{3, 3}, 6, new int[]{0, 1}),
                Arguments.of(new int[]{3, 2, 4}, 6, new int[]{1, 2}),
                Arguments.of(new int[]{3, 5, 1, 7, 5, 2, 1}, 9, new int[]{5, 3})
        );
    }

    /**
     * Builds the test matrix: every variant in {@link #VARIANTS} paired with every case from {@link #cases()}.
     * <p>
     * Each case {@code (input, target, expected)} is widened to
     * {@code (variantName, variantFn, input, target, expected)}. {@code cases()} is called once per variant
     * because a stream can only be consumed once.
     *
     * @return one {@link Arguments} row per (variant, case) pair, i.e. {@code VARIANTS.size() * cases().count()} rows
     */
    private static Stream<Arguments> provideData() {
        return VARIANTS.entrySet().stream().flatMap(variant ->
                cases().map(c -> {
                    Object[] args = c.get();
                    return Arguments.of(variant.getKey(), variant.getValue(),
                            args[0], args[1], args[2]);
                }));
    }

    @ParameterizedTest(name = "{0} - case {index}: target={3}")
    @MethodSource("provideData")
    void verifySolution(String variant, BiFunction<int[], Integer, int[]> fn,
                        int[] input, int target, int[] expected) {
        int[] actual = fn.apply(input.clone(), target);
        Arrays.sort(actual);
        int[] sortedExpected = expected.clone();
        Arrays.sort(sortedExpected);
        Assertions.assertArrayEquals(sortedExpected, actual);
    }
}
