/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null; // previous value to initialize, it's null because no left term from start
        ListNode curr = head; // the current value

        // go until the end of the linked list
        while (curr != null) {
            ListNode next = curr.next; // save the current
            curr.next = prev; // the actual reversal
            prev = curr; // save previous as current
            curr = next; // set current as next to move forward
        }
        return prev;
    }
}
