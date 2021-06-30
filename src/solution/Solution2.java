package solution;

import java.util.Objects;

/**
 * @Author: hzhq1255
 * @Mail: hzhq1255@163.com
 * @Date: 2021/5/22 0:08
 * @Desc: Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 * https://leetcode-cn.com/problems/add-two-numbers/
 * 2. 两数相加
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 */
class Solution2 {

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int[] nums1 = {9};
        int[] nums2 = {1, 9, 9, 9, 9, 9, 9, 9, 9};
        ListNode listNode1 = solution.numToListNode(999);
        ListNode listNode2 = solution.numToListNode(99999);
        ListNode result = solution.addTwoNumbers(listNode1, listNode2);
        solution.printListNode(result);
    }

    public void printListNode(ListNode listNode) {
        while (listNode != null) {
            try {
                System.out.print(listNode.val + " ");
                listNode = listNode.next;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode current = head;
        int count = 0;
        while (!(l1 == null && l2 == null)) {
            int num1 = l1 == null ? 0 : l1.val;
            int num2 = l2 == null ? 0 : l2.val;
            int num = num1 + num2 + count;
            if (num > 9) {
                count = 1;
                num = num - 10;
            } else {
                count = 0;
            }
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
            ListNode listNode = new ListNode(num);
            current.next = listNode;
            current = listNode;
        }
        if (count == 1) {
            current.next = new ListNode(count);
        }
        return Objects.requireNonNullElse(head.next, head);
    }


    public void test() {
        int[] nums1 = {9};
        int[] nums2 = {1, 9, 9, 9, 9, 9, 9, 9, 9};
        long num1 = listNodeToNum(intArrayToListNode(nums1));
        long num2 = listNodeToNum(intArrayToListNode(nums2));

        long num = num1 + num2;
        System.out.println(num1);
        System.out.println(num2);
        System.out.println(num);
        ListNode result = null;
//        while (true){
//            if (num / 10 != 0){
//                result = new ListNode(num % 10, result);
//            }else {
//                result = new ListNode(num,result);
//                break;
//            }
//            num = num / 10;
//        }

//        System.out.println(listNodeToNum(result));

    }

    public ListNode intArrayToListNode(int[] nums) {
        ListNode listNode = null;
        for (int i = nums.length - 1; i >= 0; i--) {
            listNode = new ListNode(nums[i], listNode);
        }
        return listNode;
    }

    public long listNodeToNum(ListNode listNode) {
        long num = 0;
        long count = 0;
        while (listNode != null) {
            num = num + (long) (listNode.val * Math.pow(10, count));
            count++;
            listNode = listNode.next;

        }
        return num;
    }

    public ListNode numToListNode(int num) {
        ListNode listNode = null;
        if (num == 0) {
            return new ListNode(0);
        } else {
            String nums = String.valueOf(num);
            for (int i = nums.length() - 1; i >= 0; i--) {
                int digits = nums.charAt(i) - '0';
                listNode = new ListNode(digits, listNode);
            }
        }
        return listNode;
    }

    public ListNode numToListNodeByRecurve(int num) {
        if (num == 0) {
            return null;
        } else {
            return new ListNode(num % 10, numToListNodeByRecurve(num / 10));
        }
    }

    public int listNodeToNumByRecurve(ListNode listNode) {
        if (listNode == null) {
            return 0;
        } else {
            return listNodeToNumByRecurve(listNode.next) * 10 + listNode.val;
        }
    }

    private class ListNode {

        int val = 0;
        ListNode next;

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }

        ListNode() {
        }

        ;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}


