class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            int current = map.get(s.charAt(i));
            // ensure we do not go out of bounds
            // if current value is less than the next value
            // IV = 4 (V = 5, I = 1) (-1 + 5 = 4)
            if (i + 1 < s.length() && current < map.get(s.charAt(i + 1))) {
                count -= current;
            } else {
                count += current;
            }
        }

        return count;
    }
}
