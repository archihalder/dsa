package problems.addtwonumbers;

import datastructures.ListNode;

public class AddTwoNumbers {

    public ListNode optimal(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int a = l1 != null ? l1.val : 0;
            int b = l2 != null ? l2.val : 0;
            int sum = a + b + carry;

            tail.next = new ListNode(sum % 10);
            carry = sum / 10;

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
            tail = tail.next;
        }

        return dummy.next;
    }
}
