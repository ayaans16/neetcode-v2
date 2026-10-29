class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < nums[right]) {
                // the right side has no point to be searched, shorten range
                right = mid;
            } else {
                // the left side is no use, shorten the range by making it mid + 1 
                left = mid + 1;
            }
        }
        return nums[left];
    }
}

/*
- left is the first index (0)
- right is the last index which is the length of the array - 1
- while left is less than right to make sure it's properly searched
  - mid is the middle element calculated by left + (right - left) / 2
  - check if the middle number is less than the right number (means the right side is greater than mid so the smallest value is not there)
    - if yes, shorten the right side by making the right index the middle index to shorten the range we search 
    - else we shorten the left side by making the left = mid + 1 to shorten the range
  - return nums[left] because the loop stops when left == right
*/
