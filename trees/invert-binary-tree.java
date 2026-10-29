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
    public TreeNode invertTree(TreeNode root) {
        // base case, return root if we are at null
        if (root == null) {
            return root;
        }

        // a temp node to store left
        TreeNode temp = root.left;
        // set left to the right node
        root.left = root.right;
        // set right node to temp which holds left node
        root.right = temp;
        // recursively call the functions to repeat this again
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}
