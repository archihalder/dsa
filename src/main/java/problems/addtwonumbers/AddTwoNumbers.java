package problems.addtwonumbers;

import datastructures.ListNode;

public class AddTwoNumbers {

    public ListNode optimal(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        int carry = 0;
        int sum = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int a = l1 == null ? 0 : l1.val;
            int b = l2 == null ? 0 : l2.val;
            sum = a + b + carry;

            tail.next = new ListNode(sum % 10);
            carry = sum / 10;

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
            tail = tail.next;
        }

        return dummy.next;
    }
}
