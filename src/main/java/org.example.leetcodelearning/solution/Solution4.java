package org.example.leetcodelearning.solution;

import java.util.*;

/**
 * @Author: hzhq1255
 * @Mail: hzhq1255@163.com
 * @Date: 2021/7/9 5:11 下午
 * @Desc:
 *
 * 4. 寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 *
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * 示例 3：
 *
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 * 示例 4：
 *
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 * 示例 5：
 *
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 *
 *
 * 提示：
 *
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 *
 *
 * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 */
public class Solution4 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> numList = new LinkedList<>();
        for (int i = 0, j = 0; i < nums1.length || j < nums2.length; i++, j++){
            if (i < nums1.length){
                numList.add(nums1[i]);
            }
            if (j < nums2.length){
                numList.add(nums2[j]);
            }
        }
        Collections.sort(numList);
        int len = numList.size();
        if (len % 2 == 1){
            return numList.get(len/2);
        }else {
            return (  (double) numList.get(len/2 - 1) +  (double) numList.get(len/2) ) / 2;
        }
    }

    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int[] nums = new int[nums1.length + nums2.length];
        int i = 0, j = 0, k = 0;
        while ( k < nums.length){
            if(i < nums1.length && j < nums2.length){
                if (nums1[i] > nums2[j]){
                    nums[k] = nums2[j];
                    j++;
                } else {
                    nums[k] = nums1[i];
                    i++;
                }

            } else{
                if (nums1.length > nums2.length){
                    nums[k] = nums1[i];
                    i++;
                } else if (nums1.length < nums2.length){
                    nums[k] = nums2[j];
                    j++;
                } else {
                    if (i < j){
                        nums[k] = nums1[i];
                        i++;
                    } else {
                        nums[k] = nums2[j];
                        j++;
                    }
                }
            }
            if( k == nums.length / 2 ){
                int mid = nums.length / 2;
                System.out.println("len="+nums.length + " mid="+mid);
                if(nums.length % 2 == 0){
                    return ((double) nums[k] + (double) nums[k-1])/2;
                } else {
                    return nums[k];
                }
            }
            k++;
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] nums1 = {10000};
        int[] nums2 = {10001};
        double mid = findMedianSortedArrays2(new int[]{2},new int[]{1});
        System.out.println(mid);
    }
}
