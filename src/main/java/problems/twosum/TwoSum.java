package problems.twosum;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public int[] bruteForce(int[] nums, int target) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        return new int[]{};
    }

    public int[] optimal(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int x = target - nums[i];
            if (map.containsKey(x)) {
                return new int[]{map.get(x), i};
            }
            map.put(nums[i], i);
        }

        return new int[]{};
    }
}
