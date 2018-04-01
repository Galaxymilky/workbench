package leetcode.hashtable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by dynamicniu on 2018/1/25.
 */
public class LeetcodeLinkedList {
    public static void main(String[] args) {
        LeetcodeLinkedList lcLinkedList = new LeetcodeLinkedList();
    }


    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     * <p>
     * Given 1->1->2->3->3, return 1->2->3.
     */
    public ListNode deleteDuplicates_unsort(ListNode head) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(head.val, 1);
        ListNode curNode = head;
        while (curNode != null && curNode.next != null) {
            if (map.containsKey(curNode.next.val)) {
                curNode.next = curNode.next.next;
            } else {
                curNode = curNode.next;
                map.put(curNode.next.val, 1);
            }

        }
        return head;
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode curNode = head;
        while (curNode != null && curNode.next != null) {
            if (curNode.next.val == curNode.val) {
                curNode.next = curNode.next.next;
            } else {
                curNode = curNode.next;
            }
        }
        return head;
    }

    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
