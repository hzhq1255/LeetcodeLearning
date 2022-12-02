package org.example.leetcodelearning.solution;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: hzhq1255
 * @Mail: hzhq1255@163.com
 * @Date: 2021/7/9 3:12 下午
 * @Desc:
 * 面试题 17.10. 主要元素
 * 数组中占比超过一半的元素称之为主要元素。给你一个 整数 数组，找出其中的主要元素。若没有，返回 -1 。请设计时间复杂度为 O(N) 、空间复杂度为 O(1) 的解决方案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[1,2,5,9,5,9,5,5,5]
 * 输出：5
 * 示例 2：
 *
 * 输入：[3,2]
 * 输出：-1
 * 示例 3：
 *
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 */
public class Interview17_10 {

    // Boyer-Moore 投票算法 摩尔投票
    public int majorityElement(int[] nums){
        int n = nums.length;
        int x = 0, count = 0;
        for (int num: nums){
            if (count == 0){
                x = num;
                count++;
            }else {
                count += x == num ? 1 : -1;
            }
        }
        count = 0;
        for (int num: nums) {
            if ( x == num){
                count++;
            }
        }
        return count > n / 2 ? x : -1;
    }


    // 哈希
    public int majorityElementHash(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int maxCountNum = -1 , len = nums.length;

        for(int num: nums){
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
            if(countMap.get(num) > len / 2 ){
                maxCountNum = num;
                break;
            }
        }
        return maxCountNum;
    }
}
