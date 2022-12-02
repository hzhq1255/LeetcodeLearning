package org.example.leetcodelearning.solution;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: hzhq1255
 * @Mail: hzhq1255@163.com
 * @Date: 2021/6/15 14:52
 * @Desc:
 * 279. 完全平方数
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
 *
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 * 示例 2：
 *
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 *
 * 提示：
 *
 * 1 <= n <= 104
 *
 */
public class Solution279 {

    public int[] perfectSquares = {1, 4, 9, 16, 25, 36, 49, 64, 81, 100};
    int count = 0;

    public static void main(String[] args) {
        Solution279 solution = new Solution279();
        List<Integer> nums1 = solution.getPerfectSquare(104);
        System.out.println(nums1.toString());

    }

    public int numSquares(int n) {
        return 0;
    }

//    public int backTrack(int[] nums ){
//    }

    public List<Integer> getPerfectSquare(int num){
        List<Integer> perfectSquareList = new ArrayList<>();
        for (int i = 1; i <= num ; i++){
            int sqrt = (int) Math.sqrt(i);
            if (sqrt * sqrt == i){
                perfectSquareList.add(i);
            }
        }
        return perfectSquareList;
    }

}
