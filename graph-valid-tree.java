class Solution {
    public boolean validTree(int n, int[][] edges) {
        boolean[] visited = new boolean[n];

        if (edges.length != n - 1) {
            return false;
        }
        
        int count = 0;

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] e : edges) {
            // this creates the direction
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        dfs(0, adj, visited);

        for (int j = 0; j < visited.length; j++) {
            if (visited[j] == true) {
                count++;
            }
        }

        if (count == n) {
            return true;
        }
        return false;
}

    void dfs(int node, List<List<Integer>> adj, boolean[] visited) {
        // sets the node as visited meaning we've seen it
        visited[node] = true;
        // for the next node on the list, if it's not been visited,
        // perform the function again to revisit it using recursion
        for (int next : adj.get(node)) {
            if (!visited[next]) {
                dfs(next, adj, visited); // call the function again to visit the next node
            }
        }
    }
}
