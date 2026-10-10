package problems.twosum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class TwoSumTest {

    private static final TwoSum solver = new TwoSum();

    private static final Map<String, BiFunction<int[], Integer, int[]>> VARIANTS = Map.of(
            "bruteForce", solver::bruteForce,
            "optimal", solver::optimal
    );

    private static Arguments largeData() {
        Random rand = new Random(45L);
        int n = 10_000;
        // Random values are all >= 1000, so any pair involving them sums to more than the target (100).
        // This guarantees the planted pair is the only solution.
        int min = 1_000;
        int max = 1_000_000_000;
        int fixedIndexA = 23;
        int fixedIndexB = 47;
        int fixedValueA = 49;
        int fixedValueB = 51;

        int[] input = new int[n];
        for (int i = 0; i < n; i++) {
            input[i] = rand.nextInt(min, max + 1);
        }

        input[fixedIndexA] = fixedValueA;
        input[fixedIndexB] = fixedValueB;
        int target = fixedValueA + fixedValueB;

        return Arguments.of(input, target, new int[]{fixedIndexA, fixedIndexB});
    }

    private static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(new int[]{2, 7, 11, 15}, 9, new int[]{0, 1}),
                Arguments.of(new int[]{3, 3}, 6, new int[]{0, 1}),
                Arguments.of(new int[]{3, 2, 4}, 6, new int[]{1, 2}),
                Arguments.of(new int[]{3, 5, 1, 7, 5, 2, 1}, 9, new int[]{5, 3}),
                Arguments.of(new int[]{-3, 4, 3, 90}, 0, new int[]{0, 2}),
                Arguments.of(new int[]{-1, -2, -3, -4, -5}, -8, new int[]{2, 4}),
                Arguments.of(new int[]{0, 4, 3, 0}, 0, new int[]{0, 3}),
                Arguments.of(new int[]{1, 2, 3, 4, 10, 20}, 30, new int[]{4, 5}),
                largeData()
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
