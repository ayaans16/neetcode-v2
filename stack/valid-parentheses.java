class Solution {
    public boolean isValid(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ('(') || s.charAt(i) == ('[') || s.charAt(i) == ('{')) {
            deque.push(s.charAt(i));
        } else if (s.charAt(i) == (')')) {
            if (deque.isEmpty()) {
                return false;
            }
            if (deque.peek() == '(') {
                deque.pop();
            } else {
                return false;
            }
        } else if (s.charAt(i) == ('}')) {
            if (deque.isEmpty()) {
                return false;
            }

            if (deque.peek() == '{') {
                deque.pop();
            } else {
                return false;
            }
        } else if (s.charAt(i) == (']')) {
            if (deque.isEmpty()) {
                return false;
            }
            
            if (deque.peek() == '[') {
                deque.pop();
            } else {
                return false;
            }
        }
        }

        if (deque.isEmpty()) {
            return true;
        }
        return false;
    }
}
