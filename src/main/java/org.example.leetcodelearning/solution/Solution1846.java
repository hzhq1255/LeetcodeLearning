package org.example.leetcodelearning.solution;

import java.util.ArrayList;

/**
 * @author: hzhq
 * @date 2021/7/15 7:54 下午
 * @desc:
 */
public class Solution1846 {



    public static int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        arr = radixSort(arr);
        for (int i = 0; i < arr.length; i++){
            if ( i == 0 && arr[0] > 1){
                arr[0] = 1;
            }
            if (i > 0){
                int abs = Math.abs(arr[i] - arr[i - 1]);
                if (abs > 1){
                    arr[i] = arr[i - 1] + 1;
                }
            }
        }
        return arr[arr.length - 1];
    }

        /**
         * 基数排序
         * @param array
         * @return
         */
    public static int[] radixSort(int[] array) {
        if (array == null || array.length < 2) {
            return array;
        }
        // 1.先算出最大数的位数；
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            max = Math.max(max, array[i]);
        }
        int maxDigit = 0;
        while (max != 0) {
            max /= 10;
            maxDigit++;
        }
        int mod = 10, div = 1;
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < 10; i++) {
            bucketList.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < maxDigit; i++, mod *= 10, div *= 10) {
            for (int j = 0; j < array.length; j++) {
                int num = (array[j] % mod) / div;
                bucketList.get(num).add(array[j]);
            }
            int index = 0;
            for (ArrayList<Integer> integers : bucketList) {
                for (int k = 0; k < integers.size(); k++) {
                    array[index++] = integers.get(k);
                }
                integers.clear();
            }
        }
        return array;
    }

}
