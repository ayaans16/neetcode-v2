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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // if the roots are both null, return true because they are the same
        if (p == null && q == null) {
            return true;
        }

        // if p or q is null, return false because one of them is null the other isn't
        if (p == null || q == null) {
            return false;
        }

        // if the value of p is not equal to the value of q, return false
        if (p.val != q.val) {
            return false;
        }

        // recursive step (boolean evaluation of if the left side of both trees and the right side of both trees are true)
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
