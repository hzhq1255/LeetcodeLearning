package solution;

import struct.DLinkedList;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: hzhq1255
 * @Mail: hzhq1255@163.com
 * @Date: 2021/6/30 4:50 下午
 * @Desc:
 */
public class Solution146 {

    public static class LRUCache {
        private Map<Integer, DLinkedNode> cache = new HashMap<>();
        private int size;
        private int capacity;
        private DLinkedNode head, tail;


        private int get(int key) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                return -1;
            }
            moveToFirst(node);
            return node.value;
        }



        private void put(int key, int value) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                if (capacity == cache.size()) {
                    cache.remove(tail.pre.key);
                    removeLast();
                }
                node = new DLinkedNode(key, value);
                cache.put(key, node);
                addFirst(node);
            } else {
                node.value = value;
                moveToFirst(node);
            }
        }

        public class DLinkedNode {
            int key;
            int value;
            DLinkedNode pre;
            DLinkedNode next;

            public DLinkedNode() {
            }



            public DLinkedNode(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private LRUCache(int capacity) {
            this.size = 0;
            this.capacity = capacity;
            this.head = new DLinkedNode();
            this.tail = new DLinkedNode();
            this.head.next = this.tail;
            this.tail.pre = this.head;
        }

        /**
         * 在head前插入节点
         *
         * @param node 插入节点
         */
        private void addFirst(DLinkedNode node) {
            node.pre = this.head;
            node.next = this.head.next;
            this.head.next.pre = node;
            this.head.next = node;
            size++;
        }

        private void remove(DLinkedNode node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            size--;
        }

        private void removeLast() {
            remove(this.tail.pre);
        }

        private void moveToFirst(DLinkedNode node) {
            remove(node);
            addFirst(node);
        }

        private int getSize() {
            return this.size;
        }

        private void print(){
            DLinkedNode current = head.next;
            System.out.print("{");
            while(current != tail){
                System.out.print("["+current.key+","+current.value+"]");
                current = current.next;
                if (current != tail){
                    System.out.print(",");
                }
            }
            System.out.print("}\n");
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1); // 缓存是 {1=1}
        lruCache.put(2, 2); // 缓存是 {1=1, 2=2}
        lruCache.print();
        System.out.println(lruCache.get(1));    // 返回 1
        lruCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lruCache.print();
        System.out.println(lruCache.get(2));      // 返回 -1 (未找到)
        lruCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        lruCache.print();
        System.out.println(lruCache.get(1));    // 返回 -1 (未找到)
        System.out.println(lruCache.get(3));     // 返回 3
        System.out.println(lruCache.get(4));      // 返回 4
    }
}
