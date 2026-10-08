class Solution {
    public int findCenter(int[][] edges) {
        // check first two edges
        int a = edges[0][0]; // [1,2] -> this is 1
        int b = edges[0][1]; // [1,2] --> this is 2

        if (a == edges[1][0] || a == edges[1][1]) {
            return a;
        }

        return b;
    }
}
