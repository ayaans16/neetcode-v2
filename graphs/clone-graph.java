/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        return dfs(node, new HashMap<>());
    }

    public Node dfs(Node node, Map<Node, Node> map) {
        Node curr = new Node(node.val);
        map.put(node, curr); // mapping

        for (Node neighbour : node.neighbors) {
            if (!map.containsKey(neighbour)) {
                dfs(neighbour, map);
            }
            // neighbors is a LIST !!! we can add the current neighbour of the og node
            // to the list of neighbours of the new node to make that copy

            // get the og value from the hash map to add to the neighbour of the new node
            curr.neighbors.add(map.get(neighbour));
        }
        return curr;
    }
}
