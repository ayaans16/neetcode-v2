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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode res = new ListNode(0); // holds the merged linked list
        ListNode curr = res; // holds current value

        // ensure both lists are not null
        while (list1 != null && list2 != null) {
        // if the initial head of list 1 <= list2
        if (list1.val <= list2.val) {
            // set the value in the new list to be the head of list1
            curr.next = list1;
            // next value in list 1 (traverse)
            list1 = list1.next;
        } else {
            // value of current will be value of list 2 and traverse
            curr.next = list2;
            list2 = list2.next;
        }
        // move down the new list to populate next value
        curr = curr.next;
        }

        // if there are remaining values in either list and the other is empty
        if (list1 != null) {
            curr.next = list1;
        } else {
            curr.next = list2;
        }

        // return the new list (skip initial dummy value which is 0)
        return res.next;
    }
}
