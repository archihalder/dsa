package datastructures;

import java.util.ArrayList;
import java.util.List;

// Singly Linked List
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode of(int... vals) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int v: vals) {
            curr.next = new ListNode(v);
            curr = curr.next;
        }
        return dummy.next;
    }

    public static int[] toArray(ListNode head) {
        List<Integer> out = new ArrayList<>();
        for (ListNode n = head; n != null; n = n.next) {
            out.add(n.val);
        }
        return out.stream().mapToInt(Integer::intValue).toArray();
    }
}
