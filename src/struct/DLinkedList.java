package struct;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: hzhq1255
 * @Mail: hzhq1255@163.com
 * @Date: 2021/6/29 20:42
 * @Desc:
 * 哈希链表
 */
public class DLinkedList {


    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;


    private int get(int key){
        DLinkedNode node = cache.get(key);
        if (Objects.isNull(node)){
            return -1;
        }
        moveToFirst(node);
        return node.value;
    };

    private void put(int key, int value){
        DLinkedNode node = cache.get(key);
        if (Objects.isNull(node)){
            if (capacity == cache.size()){
                cache.remove(tail.pre.key);
                removeLast();
            }
            node = new DLinkedNode(key,value);
            cache.put(key,node);
            addFirst(node);
        }else {
            node.value = value;
            moveToFirst(node);
        }
    }

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

    private DLinkedList (int capacity){
        this.size = 0;
        this.capacity = capacity;
        this.head = new DLinkedNode();
        this.tail = new DLinkedNode();
        this.head.next = this.tail;
        this.tail.pre = this.head;
    }

    /**
     * 在head前插入节点
     * @param node 插入节点
     */
    private void addFirst(DLinkedNode node){
        node.pre = this.head;
        node.next = this.head.next;
        this.head.next.pre = node;
        this.head.next = node;
        size++;
    }

    private void remove(DLinkedNode node){
        node.pre.next = node.next;
        node.next.pre = node.pre;
        size--;
    }

    private void removeLast(){
        remove(this.tail.pre);
    }

    private void moveToFirst(DLinkedNode node){
        remove(node);
        addFirst(node);
    }

    private int getSize(){
        return this.size;
    }




}
