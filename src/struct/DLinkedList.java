package struct;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: hzhq1255
 * @Mail: hzhq1255@163.com
 * @Date: 2021/6/29 20:42
 * @Desc:
 */
public class DLinkedList {


    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;

    public class DLinkedNode {
        int key;
        int value;
        DLinkedNode pre;
        DLinkedNode next;

        public DLinkedNode() {};
        public DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public DLinkedList (int capacity){
        this.size = 0;
        this.capacity = capacity;
        this.head = new DLinkedNode();
        this.tail = new DLinkedNode();
        this.head.next = this.tail;
        this.tail.pre = this.head;
    }

    public void addNodeFirst(DLinkedNode node){
        node.pre = this.head;
        node.next = this.head.next;
        this.head.next.pre = node;
        this.head.next = node;
        size++;
    }
}
