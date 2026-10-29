class Solution {
    public int longestConsecutive(int[] nums) {
        // sort array
        Arrays.sort(nums);

        // initialize current streak and longest streak held
        int currentStreak = 1;
        int longest = 0;

        // base case: if length is 0 of array, return 0
        if (nums.length == 0) {
            return 0;
        }

        // iterate through array skipping last element
        for (int i = 0; i < nums.length - 1; i++) {
            // if value at index 1 up - 1 is equal to current index, increment current streak
            if (nums[i + 1] - 1 == nums[i]) {
                currentStreak++;
            // if they're equal, do nothing
            } else if (nums[i+1] == nums[i]) {
                currentStreak = currentStreak;
            // if they're not the same, determine the longest streak
            // by comparing whether the current streak is larger than
            // the previous longest streak
            } else {
                longest = Math.max(currentStreak, longest);
                currentStreak = 1;
            }
        }
        
        // return the longest streak
        return Math.max(currentStreak, longest);
    }
}

/*
  this is asking for any sequence that is the longest in the array
*/
