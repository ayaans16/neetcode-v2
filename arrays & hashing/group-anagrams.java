class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        // the key will be a string
        // the value will be a list of strings that are anagrams

        // for each string str in the array of strings strs
        for (String str : strs) {
            // convert the individual string to a char array
            char[] ch = str.toCharArray();
            // sort char array 
            Arrays.sort(ch);
            // convert the char array into a string
            String key = new String(ch);

            // if the map does not contain the key, add it with a list as the value
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            // if it does exist, find the key and add the raw string into it
            map.get(key).add(str);
        }
        // return a list containing all the values only in the map
        return new ArrayList<>(map.values());
    }
}
