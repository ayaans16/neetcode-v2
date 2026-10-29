class Solution {
    public int countComponents(int n, int[][] edges) {
        int count = 0;
        boolean[] visited = new boolean[n];
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        for (int j = 0; j < visited.length; j++) {
            if (!visited[j]) {
                dfs(j, adj, visited);
                count++;
            }
        }
        return count;
    }

    void dfs(int node, List<List<Integer>> adj, boolean[] visited) {
        visited[node] = true;
        for (int next : adj.get(node)) {
            if (!visited[next]) {
                dfs(next, adj, visited);
            }
        }
    }
}
