/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        // making sure the value of fast and the value after fast is not null so that we can traverse through the list
        while (fast != null && fast.next != null) {
            // move 1 step for slow, 2 for fast
            slow = slow.next;
            fast = fast.next.next;

            // if nodes are equal
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
