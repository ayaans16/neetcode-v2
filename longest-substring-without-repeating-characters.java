class Solution {
    public int lengthOfLongestSubstring(String s) {
        // hash map contains the character and which index it was seen at
        Map<Character, Integer> lastSeen = new HashMap<>();

        // keep track of the window and the longest substring yet seen
        int left = 0;
        int best = 0;

        // iterate through each character of the string 
        for (int right = 0; right < s.length(); right++) {
            // get the char at current index of string
            char c = s.charAt(right);

            // if the character was seen already AND the index it was seen at is greater than
            // the current window, we shorten the window to check less characters because those characters are now irrelevant since they've been seen before
            if (lastSeen.containsKey(c) && lastSeen.get(c) >= left) {
                // shorten the window size
                left = lastSeen.get(c) + 1;
            }
            // if not seen, add the character and index to hash map
            lastSeen.put(c, right);
            // determine longest substring by comparing it to what we have vs if we have a longer substring based on current index - what the left value is (the start of the window) and add 1 to get the number of characters we have exactly
            best = Math.max(best, right - left + 1);
        }
        // return the longest substring
        return best;
    }
}
