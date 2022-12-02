package org.example.leetcodelearning.struct;

import javax.sound.midi.Soundbank;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author hzhq1255
 * @version 1.0
 * @since 2022-12-02 下午11:13
 * <p>
 * 二叉树
 */
public class BinaryTree<T> {


    public BinaryTree() {
    }

    public static class Node<T> {
        public Node<T> left;
        public Node<T> right;
        public T data;

        public Node(T data) {
            this.data = data;
        }

        public Node() {
        }
    }


    int size = 0;

    /**
     * 通过前序遍历创建二叉树
     *
     * @param arr 前序遍历的二叉树数组
     * @return 返回根节点
     */
    public Node<T> createTree(T[] arr) {
        if (arr.length == 0) {
            return null;
        }
        T data = arr[0];
        T[] newArr = Arrays.copyOfRange(arr, 1, arr.length);
        System.out.printf("newArr = %s\n", Arrays.stream(arr).map(String::valueOf).collect(Collectors.joining(",")));
        Node<T> node = arr[0] != null ? new Node<>(arr[0]) : null;
        if (node == null){
            return null;
        }
        node.left = createTree(newArr);
        node.right = createTree(newArr);


//
        return node;
    }


    public static void main(String[] args) {
        Integer[] arr = {1, 2, null, 4, null, null, 3, 5, null, null, null};
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        Node<Integer> root = binaryTree.createTree(arr);
        binaryTree.preorderRecur(root);
    }


    /**
     * 二叉树前序遍历 (递归)
     * 访问节点顺序 根节点->左节点->右节点
     * A
     * B     C
     * D  E  F
     * A->B->D->C->E->F
     *
     * @param root 根节点
     */
    public void preorderRecur(Node<T> root) {
        if (Objects.isNull(root)) {
            return;
        }
        System.out.printf("%s->", root.data);
        preorderRecur(root.left);
        preorderRecur(root.right);
    }

    /**
     * 二叉树前序遍历
     *
     * @param root 根节点
     */
    public void preorderLoop(Node<T> root) {
        if (Objects.isNull(root)) {
            return;
        }
        Stack<Node<T>> stack = new Stack<>();
        Node<T> cur;
        stack.add(root);
        while (!stack.isEmpty()) {
            cur = stack.firstElement();
            stack.pop();
            System.out.printf("%s->", cur.data);
            if (cur.right != null) {
                stack.add(cur.right);
            }
            if (cur.left != null) {
                stack.add(cur.left);
            }
        }

    }
}
