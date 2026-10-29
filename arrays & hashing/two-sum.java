class Solution {
    public int[] twoSum(int[] nums, int target) {
        // target - one number = second number
        int sol[] = new int[2];

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                sol[0] = i;
                sol[1] = map.get(target - nums[i]);
            }
            map.put(nums[i], i);
        }
        return sol;
    }
}
