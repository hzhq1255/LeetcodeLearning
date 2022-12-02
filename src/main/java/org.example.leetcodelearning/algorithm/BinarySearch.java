package org.example.leetcodelearning.algorithm;

/**
 * @author: hzhq
 * @date 2021/7/19 3:34 下午
 * @desc:
 */
public class BinarySearch {

    /**
     *
     * @param nums
     * @param num
     * @return
     *  1 2 3 4 5
     */
    public static int binarySearchNum(int[] nums, int num){
        int left = 0, right = nums.length - 1, mid = nums.length / 2;
        while (left <= right){
            if (nums[mid] > num){
                right = mid - 1;
            } else if (nums[mid] < num){
                left = mid + 1;
            } else {
                return mid;
            }
            mid = left +  ((right - left) >> 1);
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        System.out.println(binarySearchNum(nums,3));
    }
}
