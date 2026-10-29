class Solution {
    public int numIslands(char[][] grid) {
        // check null case
        if (grid == null || grid.length == 0) {
            return 0;
        }

        // keep a count of the islands
        int count = 0;
        // O(n^2)
        // go through rows and columns
        // grid[0].length accesses the first row and counts # of columns
        // if the value is 1, increment count and do dfs
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == '1') {
                    count++;
                    dfs(grid, r, c);
                }
            }
        }
        return count;
    }

    void dfs(char[][] grid, int r, int c) {
    // if row # is less than 0
    // if row # is >= row length
    // if column # is less than 0
    // if column # is >= column length
    // if value at current index is not 1
    // return
    if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] != '1') {
        return;
    }

        // mark value as unvisited so it's not used again
        grid[r][c] = '0';

        dfs(grid, r + 1, c); // down
        dfs(grid, r - 1, c); // up
        dfs(grid, r, c + 1); // right
        dfs(grid, r, c - 1); // left
    }
}
