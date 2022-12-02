package org.example.leetcodelearning.solution;

/**
 * @author: hzhq
 * @date 2021/7/16 10:13 上午
 * @desc:
 */
public class Solution34 {
    public static int search(int[] nums, int target) {
        if (nums.length == 0){
            return 0;
        }
        int left = 0, right = nums.length - 1, mid = (nums.length - 1) / 2;
        while (left <= right) {
            System.out.println("l=" + left + " r=" + right + " m=" + mid);
            if (nums[mid] > target) {
                right--;
            } else if (nums[mid] < target) {
                left++;
            } else {
                break;
            }
            mid = (left + right) / 2;
        }
        if (nums[mid] != target){
            return 0;
        }
        for (right = mid; right < nums.length;){
            if (nums[right] == target){
                right++;
            } else {
                break;
            }
        }
        for (left = mid; left >= 0;){
            if (nums[left] == target){
                left--;
            }else {
                break;
            }
        }
        return --right - ++left + 1;
    }

    public static void main(String[] args) {
        int[] nums = {2,10};
        System.out.println(search(nums,10));
    }

}
