package org.example.leetcodelearning.solution;

import java.util.*;

/**
 * @Author: hzhq1255
 * @Mail: hzhq1255@163.com
 * @Date: 2021/7/7 10:27 上午
 * @Desc: 1711. 大餐计数
 * 大餐 是指 恰好包含两道不同餐品 的一餐，其美味程度之和等于 2 的幂。
 * <p>
 * 你可以搭配 任意 两道餐品做一顿大餐。
 * <p>
 * 给你一个整数数组 deliciousness ，其中 deliciousness[i] 是第 i 道餐品的美味程度，返回你可以用数组中的餐品做出的不同 大餐 的数量。结果需要对 10e9 + 7 取余。
 * <p>
 * 注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：deliciousness = [1,3,5,7,9]
 * 输出：4
 * 解释：大餐的美味程度组合为 (1,3) 、(1,7) 、(3,5) 和 (7,9) 。
 * 它们各自的美味程度之和分别为 4 、8 、8 和 16 ，都是 2 的幂。
 * 示例 2：
 * <p>
 * 输入：deliciousness = [1,1,1,3,3,3,7]
 * 输出：15
 * 解释：大餐的美味程度组合为 3 种 (1,1) ，9 种 (1,3) ，和 3 种 (1,7) 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= deliciousness.length <= 105
 * 0 <= deliciousness[i] <= 220
 *
 * 一、 暴力破解必超时
 * 二、 哈希表
 */
public class Solution1711 {


    public int maxNum = 2 << 20;
    public int maxTwoSum = 0;
    public int minTwoSum = 0;
    public long count = 0;
    public Map<Integer, Integer> numMap = new HashMap<>();

    public int countPairs(int[] deliciousness) {
        Arrays.stream(deliciousness).forEach(value -> {
            numMap.put(value,numMap.getOrDefault(value,0) + 1);
        });
//        numMap.put(32,100000);
        System.out.println(numMap);
        while (maxNum > 0){
            for (Integer num: numMap.keySet()){
                long currentCount = 0;
                Integer anotherNum = maxNum - num;
                if (anotherNum.equals(num)){
                    if (numMap.get(num) > 1){
                        System.out.println("maxNum=" + maxNum +" num=" + num + " anotherNum=" + anotherNum + " value=" + numMap.get(num));
                        int value = numMap.get(num);
                        System.out.println(value * (value - 1));
                        currentCount = (long) numMap.get(num) * (numMap.get(num) - 1);
                        count += currentCount;
                        System.out.println("count=" + count + " currentCount=" + currentCount);
                    }
                }else {
                    if (numMap.containsKey(anotherNum)){
                        System.out.println("maxNum=" + maxNum +" num=" + num + " anotherNum=" + anotherNum);
                        currentCount = (long) numMap.get(anotherNum) * numMap.get(num);
                        count += currentCount;
                        System.out.println("count=" + count + " currentCount=" + currentCount);
                    }
                }
            }
            maxNum =  maxNum >> 1;
        }
        return (int) ( count  % ( (int) Math.pow(10,9) + 7));
    }


    /**
     * 优化过的 hash 方法
     * @param deliciousness
     * @return
     */
    public int countPairs1(int[] deliciousness) {
        Arrays.stream(deliciousness).forEach(value -> {
            if (maxTwoSum <= value * 2){
                maxTwoSum = value * 2;
            }
            if ( minTwoSum >= value * 2){
                minTwoSum = value * 2;
            }
            numMap.put(value,numMap.getOrDefault(value,0) + 1);
        });
        //System.out.println(numMap);
        while (maxNum >= minTwoSum && maxNum != 0){
            if ( maxNum > maxTwoSum){
                maxNum = maxNum >> 1;
                continue;
            }
            //System.out.println(maxNum + " " + maxTwoSum + " " + minTwoSum);
            for (Integer num: numMap.keySet()){
                if ( num >= 0 && num <= maxNum >> 1){
                    long currentCount = 0;
                    Integer anotherNum = maxNum - num;
                    if (anotherNum.equals(num)){
                        if (numMap.get(num) > 1){
                            currentCount = ((long) numMap.get(num) * (numMap.get(num) - 1)) / 2;
                            count += currentCount;
                        }
                    }else {
                        if (numMap.containsKey(anotherNum)){
                            currentCount = (long) numMap.get(anotherNum) * numMap.get(num);
                            count += currentCount;
                        }
                    }
                }
            }
            maxNum =  maxNum >> 1;
        }
        return (int) ( count  % ( (int) Math.pow(10,9) + 7));
    }


    public boolean isTwoPower(int number) {
        int result = number <= 0 ? -1 : number & number - 1;
        return result == 0;
    }

    public static void main(String[] args) {
        int[] test1 = {1,3,5,7,9};
        int[] test2 = {1,1,1,3,3,3,7};
        int[] test3 = {2,3,1,4};
        Solution1711 solution = new Solution1711();
        int count = solution.countPairs(test2);
        System.out.println(count);
    }


}
