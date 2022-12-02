package org.example.leetcodelearning.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: hzhq
 * @date 2021/7/20 3:09 下午
 * @desc:
 *
 * 排列组合实现
 */
public class PermutationAndCombination {


    // 公式实现

    /**
     * 阶乘
     * @param n 阶乘数
     * @return 返回阶乘结果
     */
    private static long factorial(int n){
        long result = 1;
        while (n > 0){
            result *= n--;
        }
        return result;
    }

    /**
     * 数学上的排列公式实现
     * A(m,n) = n!/(n - m)!
     * @param m 上标
     * @param n 下标
     * @return 排列结果
     */
    private static long mathPermutation(int m , int n ){
        return m > n ? 0 : factorial(n) / factorial(n - m);
    }

    /**
     * 数学上的组合公式实现
     *  C(m,n) = A(m,n)/m! = n! /( m! *(n - m)!)
     * @param m 上标
     * @param n 下标
     * @return 排列结果
     */
    private static long mathCombination(int m , int n ){
        return m > n ? 0 : factorial(n) / ( factorial(m) * factorial(n - m));
    }


    // ------------分割线-----------------
    // 以下为枚举所有类型

    // 存储所有排列的结果
    private static List<List<Integer>> allPermutationResult = new ArrayList<>();
    // 判断数组中的元素是否被使用
    private static boolean[] used;

    /**
     * 枚举所有排列形式
     * 比如 1,2,3 排列 有6种
     * 1,2,3
     * 1,3,2
     * 2,1,3
     * 2,3,1
     * 3,1,2
     * 3,2,1
     * @param nums 数组
     * @return 返回记录的数组列表
     */
    public static List<List<Integer>> enumPermutation(int[] nums){
        used = new boolean[nums.length];
        List<Integer> preList = new ArrayList<>();
        allPermutation(nums,0,preList);
        return allPermutationResult;
    }

    /**
     * 回溯思想寻找所有的排列
     * @param nums 给定数组
     * @param index 当前考察的索引位置
     * @param preList 先前排列好的子序列
     */
    private static void allPermutation(int[] nums, int index, List<Integer> preList){
        if (index == nums.length){
            // 重新创建对象，防止引用同一个对象
            allPermutationResult.add(new ArrayList<>(preList));
            return;
        }

        for (int i = 0; i < nums.length; i++){
            if (!used[i]){
                // 添加元素
                preList.add(nums[i]);
                // 将该元素状态置为使用
                used[i] = true;
                // 递归寻找除这个元素剩余未使用元素
                // 举例
                // 1 - 2
                // 1 - 3
                allPermutation(nums,index + 1,preList);
                // 回溯状态
                //  2 3 移除
                preList.remove(preList.size() - 1);
                //  状态 置为 false
                used[i] = false;
            }
        }

    }

    // ------组合算法实现---------

    // 存储所有组合的结果
    private static List<List<Integer>> combineResult = new ArrayList<>();


    /**
     * 枚举所有类型
     * @param nums 数组
     * @param k 几个元素的组合
     * @return 记录的组合
     */
    private static List<List<Integer>> enumCombine(int[] nums, int k){
        if (nums.length == 0 || k <= 0){
            return combineResult;
        }
        List<Integer> combinations = new ArrayList<>();
        generateCombinations(nums,k,0,combinations);
        return combineResult;
    }

    private static void generateCombinations(int[] nums, int k, int start, List<Integer> combinations){
        if (combinations.size() == k){
            combineResult.add(new ArrayList<>(combinations));
            return;
        }
        // 从 0 开始
        for (int i = start; i < nums.length - (k - combinations.size()) + 1; i++){
            combinations.add(nums[i]);
            generateCombinations(nums, k, i + 1,combinations);
            // 回溯
            combinations.remove(combinations.size() - 1);
        }
    }



    public static void main(String[] args) {

        // Math 计算
        System.out.println("---------排列组合数学计算---------");
        // A(2,5)
        System.out.println(mathPermutation(2,5));
        // C(2,5)
        System.out.println(mathCombination(2,5));

        // 枚举 排列组合
        int[] nums = {1,2,3};
        System.out.println("---------枚举排列组合结果--------");
        // 全排列
        System.out.println(enumPermutation(nums).toString());
        // 元素个数为 2 进行组合
        System.out.println(enumCombine(nums,2).toString());


    }
}
