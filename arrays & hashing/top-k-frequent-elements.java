class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // number of elements in output = k
        int sol[] = new int[k];
        // key will be integer, value will be # of times it appears
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                // initial count will be 1
                map.put(nums[i], 1);
            } else {
                // increment the # of times the number was seen
                map.put(nums[i], map.get(nums[i]) + 1);
            }
        }

        // dump all entries into a list
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(map.entrySet());

        // sort using lambda
        // a and b represent entries in the list
        entryList.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));

        for (int i = 0; i < k; i++) {
            sol[i] = entryList.get(i).getKey();
        }

        return sol;

    }
}
