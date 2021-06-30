package struct;

import java.util.List;

/**
 * @Author: hzhq1255
 * @Mail: hzhq1255@163.com
 * @Date: 2021/6/7 14:09
 * @Desc:
 */
public class MyLinkedList<T> {

    int size;
    // 头节点
    ListNode<T> head = null;

    public MyLinkedList() {
        this.size = 0;
        this.head = new ListNode<>(null);
    }

    void addAtIndex(int index, T val){
        if (index > size ) return;
        if (index < 0 ) index = 0;
        ListNode<T> pre = head;
        for (int i = 0; i < index; i++){
            pre = pre.next;
        }
        ListNode<T> addNode = new ListNode<>(val);
        addNode.next = pre.next;
        pre.next = addNode;
        size++;
    }

    void addAtHead(T val){
        addAtIndex(0,val);
    }

    void addAtTail(T val){
        addAtIndex(size,val);
    }

    void deleteAtIndex(int index){
        if (index < 0 && index >= size){
            return;
        }
        ListNode<T> pre = head;
        for (int i = 0; i < index; i++){
            pre = pre.next;
        }
        pre.next = pre.next.next;
        size--;
    }

    void delete(T val){
        ListNode<T> pre = head;
        while (pre.next != null){
            if (pre.next.val == val){
                pre.next = pre.next.next;
                size--;
                continue;
            }
            pre = pre.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("size = ").append(this.size).append(", ").append("[ ");
        ListNode<T> listNode = head.next;
        while (listNode.next != null){
            builder.append(listNode.val).append(" , ");
            listNode = listNode.next;
        }
        builder.append(listNode.val).append(" ]");
        return builder.toString();
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> list1 = new MyLinkedList<>();
        list1.addAtHead(1);
        list1.addAtTail(2);
        list1.addAtHead(3);
        list1.addAtHead(2);
        System.out.println(list1);
        list1.delete(2);
        System.out.println(list1);
    }
}
