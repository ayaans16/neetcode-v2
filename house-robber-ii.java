class Solution {
    public int rob(int[] nums) {
        int n = nums.length;

        if (n == 1) {
            return nums[0];
        } 
        
        // two for loops: one for each set of houses
        // set 1: every house but the last one
        // set 2: every house but the first one
        // find max between both

        // two vars will hold values
        // one will hold val of 2 houses down + current one (a2)
        // one will hold val of prev house (a1)
        // once we change houses, values will shift

        int a1 = 0;
        int a2 = 0;

        for (int i = 0; i <= n - 2; i++) {
            int curr = Math.max(a1, a2 + nums[i]);
            a2 = a1;
            a1 = curr;
        }

        int b1 = 0;
        int b2 = 0;
        for (int j = 1; j <= n - 1; j++) {
            int curr = Math.max(b1, b2 + nums[j]);
            b2 = b1;
            b1 = curr;
        }

        return Math.max(a1, b1);
    }
}
