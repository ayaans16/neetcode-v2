class Solution {
    public int[] productExceptSelf(int[] nums) {
        int sol[] = new int[nums.length];
        sol[0] = 1;

        // everything on the left
        for (int i = 1; i < nums.length; i++) { // start at index 1
            sol[i] = sol[i - 1] * nums[i - 1];
        }

        // everything on the right
        // nothing on the right of the last number
        int suffix = 1;
        for (int j = nums.length - 1; j >= 0; j--) {
            sol[j] = sol[j] * suffix;
            suffix = suffix * nums[j];
        }

        return sol;
    }
}
