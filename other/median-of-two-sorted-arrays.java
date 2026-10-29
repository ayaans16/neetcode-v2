class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 1. merge the array
        // 2. calc median

        int[] merged = new int [nums1.length + nums2.length];
        System.arraycopy(nums1, 0, merged, 0, nums1.length);
        System.arraycopy(nums2, 0, merged, nums1.length, nums2.length);
        
        Arrays.sort(merged);

        // calculate median
        // 1. if it's an even length then we have to combine both middle #s and divide by 2
        // 2. if it's an odd length then we have to just get the middle number easily

        double median = 0.00;

        // % = no remainder
        if (merged.length % 2 == 0) {
            median = (double) (merged[merged.length / 2 - 1] + (double) merged[merged.length / 2]) / 2;
        } else {
            median = (double) merged[merged.length / 2];
        }
        return median;
    }
}
