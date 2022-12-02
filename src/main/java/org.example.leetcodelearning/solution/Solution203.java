package org.example.leetcodelearning.solution;

/**
 * @Author: hzhq1255
 * @Mail: hzhq1255@163.com
 * @Date: 2021/6/6 23:34
 * @Desc:
 */
public class Solution203 {
    public static void main(String[] args) {

    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public ListNode removeElements(ListNode head, int val) {

        head = new ListNode(0,head);
        ListNode node = head;
        while(node.next != null){
            if(node.next.val == val){
                node.next = node.next.next;
                continue;
            }
            node = node.next;
        }
        return head.next;
    }

    // 递归
    public ListNode removeElementsRecursive(ListNode head, int val){
        if (head == null){
            return null;
        }
        head.next = removeElementsRecursive(head.next,val);
        return head.val == val ? head.next : head;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
