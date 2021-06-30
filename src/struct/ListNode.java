package struct;

import solution.Solution203;

/**
 * @Author: hzhq1255
 * @Mail: hzhq1255@163.com
 * @Date: 2021/6/7 14:08
 * @Desc:
 */
public class ListNode<T> {

    T val;
    ListNode<T> next;

    ListNode() {
    }

    public ListNode(T val){
        this.val = val;
    }


    public ListNode(T val, ListNode<T> next) {
        this.val = val;
        this.next = next;
    }
}
