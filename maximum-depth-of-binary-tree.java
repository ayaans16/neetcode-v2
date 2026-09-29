/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int maxDepth(TreeNode root) {
        // base case, if root is null return 0
        if (root == null) {
            return 0;
        }

        // recursively check the depth of left and right
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        // return 1 (root) + the max between left and right
        return 1 + Math.max(leftDepth, rightDepth);
    }
}
