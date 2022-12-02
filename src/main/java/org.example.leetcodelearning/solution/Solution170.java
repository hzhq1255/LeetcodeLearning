package org.example.leetcodelearning.solution;

import java.util.*;

/**
 * @Author: hzhq1255
 * @Mail: hzhq1255@163.com
 * @Date: 2021/7/6 7:21 下午
 * @Desc:
 */
public class Solution170 {

    // 哈希表
    public static class TwoSum {
        public List<Integer> nums;
        public Set<Integer> sumSet;
        public Map<Integer,Integer> numMap;

        /** Initialize your data structure here. */
        public TwoSum() {
            nums = new ArrayList();
            sumSet = new HashSet<>();
            numMap = new HashMap<>();
        }

        /** Add the number to an internal data structure.. */
        public void add(int number) {
            if (this.numMap.containsKey(number)) {
                this.numMap.put(number, this.numMap.get(number) + 1);
            } else {
                this.numMap.put(number, 1);
            }

        }

        /** Find if there exists any pair of numbers which sum is equal to the value. */
        public boolean find(int value) {
            for ( Map.Entry<Integer, Integer> entry: this.numMap.entrySet()){
                int result = value - entry.getKey();
                if(result == entry.getKey()){
                    if(entry.getValue() > 1){
                        return true;
                    }
                } else {
                    if(this.numMap.containsKey(result)){
                        return true;
                    }
                }
            }
            return false;
        }
    }

    /**
     *     双指针 二分查找
     *     其实可以在 add 的时候 直接在插入的时候就排序好
     */
    class TwoSum1 {

        public List<Integer> nums;
        public boolean isSorted = false;

        /** Initialize your data structure here. */
        public TwoSum1() {
            this.nums = new ArrayList<>();
        }

        /** Add the number to an internal data structure.. */
        public void add(int number) {
            this.nums.add(number);
            this.isSorted = false;
        }

        /** Find if there exists any pair of numbers which sum is equal to the value. */
        public boolean find(int value) {
            if(!this.isSorted){
                Collections.sort(this.nums);
                this.isSorted = true;
            }
            int left = 0, right = this.nums.size() - 1;
            while(left < right){
                int sum = nums.get(left) + nums.get(right);
                if(sum < value){
                    left++;
                } else if ( sum > value){
                    right--;
                } else{
                    return true;
                }
            }
            return false;
        }


    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        twoSum.add(1);

    }

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */
}
