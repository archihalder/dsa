package problems.addtwonumbers;

import datastructures.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class AddTwoNumbersTest {

    private static final AddTwoNumbers solver = new AddTwoNumbers();

    private static final Map<String, BiFunction<ListNode, ListNode, ListNode>> VARIANTS = Map.of(
            "optimal", solver::optimal
    );

    private static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(new int[]{2, 4, 3}, new int[]{5, 6, 4}, new int[]{7, 0, 8}),
                // both zero
                Arguments.of(new int[]{0}, new int[]{0}, new int[]{0}),
                // different lengths with carry propagating past the shorter list
                Arguments.of(new int[]{9, 9, 9, 9, 9, 9, 9}, new int[]{9, 9, 9, 9}, new int[]{8, 9, 9, 9, 0, 0, 0, 1}),
                // final carry creates a new node
                Arguments.of(new int[]{5}, new int[]{5}, new int[]{0, 1}),
                // single digit + multi digit, no carry
                Arguments.of(new int[]{1}, new int[]{2, 3, 4}, new int[]{3, 3, 4}),
                // longer first list
                Arguments.of(new int[]{2, 3, 4}, new int[]{1}, new int[]{3, 3, 4}),
                // carry ripples through the whole list
                Arguments.of(new int[]{1}, new int[]{9, 9, 9}, new int[]{0, 0, 0, 1}),
                // adding zero
                Arguments.of(new int[]{3, 2, 1}, new int[]{0}, new int[]{3, 2, 1}),
                // carry in the middle only
                Arguments.of(new int[]{1, 8, 1}, new int[]{1, 2, 1}, new int[]{2, 0, 3})
        );
    }

    private static Stream<Arguments> provideData() {
        return VARIANTS.entrySet().stream().flatMap(variant ->
                cases().map(c -> {
                    Object[] args = c.get();
                    return Arguments.of(variant.getKey(), variant.getValue(),
                            args[0], args[1], args[2]);
                }));
    }

    @ParameterizedTest(name = "{0} - case {index}")
    @MethodSource("provideData")
    void verifySolution(String variant, BiFunction<ListNode, ListNode, ListNode> fn,
                        int[] l1, int[] l2, int[] expected) {
        ListNode actual = fn.apply(ListNode.of(l1), ListNode.of(l2));
        Assertions.assertArrayEquals(expected, ListNode.toArray(actual));
    }
}
