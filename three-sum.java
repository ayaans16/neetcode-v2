class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    
    List<List<Integer>> list = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
        if (i > 0 && nums[i] == nums[i - 1]) {
            continue;
        }

        int j = i + 1;
        int k = nums.length - 1;

        while (j < k) {
            int total = nums[i] + nums[j] + nums[k];
            if (total > 0) {
                k--;
            } else if (total < 0) {
                j++;
            } else {
                list.add(Arrays.asList(nums[i], nums[j], nums[k]));
                j++; // move j to keep iterating

                while (j < k && nums[j] == nums[j - 1]) {
                    j++; // skip dupes
                }
            }
        }
    }
    return list;
    }
}

/* 
- start with a list containing a list of integers
- iterate through the array
- if i > 0 and if the current number at index i is the same as the one before, skip
- j is the index after i
- k is the last index
- make sure j is less than k
- do a total of all indices
- if total is greater than 0, decrease k so we go to second last index
- else if total is less than 0, we move j up to go the second index after i
- if they're equal to 0, we add the three indices into the array list
  - we also check while the index of j is the same as the one before it and j < k to increase j


*/
