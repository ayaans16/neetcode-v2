class Solution {
    public int climbStairs(int n) {
        int current = 1; // ways(1) -> 1 way to go up 1 stair
        int previous = 1; // ways(0) --> 1 way to go nowhere (stay at 0th stair)

        // from 1 to n
        for (int i = 1; i < n; i++) {
            // the next stair is ways(1) + ways(0)
            int next = current + previous;
            // previous is now ways(1)
            previous = current;
            // current is ways(1) + ways(0) which is 2 then we repeat by
            // updating the next value with the new current value we have
            current = next;
        }

        return current;
    }
}