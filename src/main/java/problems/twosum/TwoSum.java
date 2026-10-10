package problems.twosum;

import java.util.HashMap;

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
        HashMap<Integer, Integer> hashmap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int x = target - nums[i];
            if (hashmap.containsKey(x)) {
                return new int[]{hashmap.get(x), i};
            } else {
                hashmap.put(nums[i], i);
            }
        }

        return new int[]{};
    }
}
