package org.example.leetcodelearning.solution;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: hzhq1255
 * @Mail: hzhq1255@163.com
 * @Date: 2021/7/9 10:40 上午
 * @Desc:
 * 930. 和相同的二元子数组
 * 给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。
 *
 * 子数组 是数组的一段连续部分。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,0,1,0,1], goal = 2
 * 输出：4
 * 解释：
 * 如下面黑体所示，有 4 个满足题目要求的子数组：
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * 示例 2：
 *
 * 输入：nums = [0,0,0,0,0], goal = 0
 * 输出：15
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * nums[i] 不是 0 就是 1
 * 0 <= goal <= nums.length
 */
public class Solution930 {

    /**
     * 哈希表
     * @param nums
     * @param goal
     * @return
     *
     * goal = sum[j] - sum[i];
     *
     */
    public static int numSubarraysWithSum1(int[] nums, int goal) {
        int sum = 0;
        // 记录的hashMap
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        int ret = 0;
        for (int num : nums) {
            cnt.put(sum, cnt.getOrDefault(sum, 0) + 1);
            sum += num;
            ret += cnt.getOrDefault(sum - goal, 0);
        }
        System.out.println(cnt);
        return ret;
    }

    public static int test1(int[] nums, int goal){
        int count = 0, sum = 0;
        Map<Integer,Integer> sumMap = new HashMap<>();
        for (int num: nums){
            // 记录前缀和出现的个数
            sumMap.put(sum,sumMap.getOrDefault(sum, 0) + 1);
            sum += num;
            count += sumMap.getOrDefault(sum - goal, 0);
        }
        return count;
    }


    // 双指针
    public static int numSubarraysWithSum2(int[] nums, int goal) {
        int n = nums.length;
        int left1 = 0, left2 = 0, right = 0;
        int sum1 = 0, sum2 = 0;
        int ret = 0;
        while (right < n) {
            sum1 += nums[right];
            while (left1 <= right && sum1 > goal) {
                sum1 -= nums[left1];
                left1++;
            }
            sum2 += nums[right];
            while (left2 <= right && sum2 >= goal) {
                sum2 -= nums[left2];
                left2++;
            }
            System.out.println(left1 + " " + left2);
            ret += left2 - left1;
            right++;
        }
        System.out.println(ret);
        return ret;
    }




    public static void main(String[] args) {
        numSubarraysWithSum2(new int[]{1,0,1,0,1},2);
    }
}
