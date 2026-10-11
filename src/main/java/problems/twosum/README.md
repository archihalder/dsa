# Two Sum

## Problem Statement

Given an array of integers `nums` and an integer `target`, return the indices of the two numbers
such that they add up to `target`. Each input has exactly **one** solution. You may **not** use the
same element twice. You can return the answer in any order.

## Constraints

- `2 <= nums.length <= 10^4`
- `-10^9 <= nums[i] <= 10^9`
- `-10^9 <= target <= 10^9`
- Only one valid answer exists.

## Example

```
nums = [2, 7, 11, 15], target = 9
output = [0, 1]

because nums[0] + nums[1] = 2 + 7 = 9
```

## Solution

In both approaches, n is the size of `nums`.

1. **Brute Force**:
    - Use a nested loop to pick an element and check the rest of the elements in `nums`.
    - The inner loop starts at `i + 1`, so an element is never paired with itself and no pair is
      checked twice.
    - **Time Complexity**: O(n^2).
    - **Space Complexity**: O(1).

2. **Optimal**:
    - Traverse `nums`, and for each element compute its complement, i.e., `target - nums[i]`.
    - If the complement is already in the map, return its index along with `i`.
    - Otherwise, store the current element and its index in the map.
    - Check before storing: storing first would let an element match itself
      (e.g. `nums = [3, 2, 4], target = 6` would wrongly return `[0, 0]`).
    - **Time Complexity**: O(n).
    - **Space Complexity**: O(n).
