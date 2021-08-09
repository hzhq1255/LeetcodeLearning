package solution;

import java.util.*;

/**
 * @author: hzhq
 * @date 2021/7/19 2:45 下午
 * @desc:
 * 239. 滑动窗口最大值
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口中的最大值。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * 示例 2：
 *
 * 输入：nums = [1], k = 1
 * 输出：[1]
 * 示例 3：
 *
 * 输入：nums = [1,-1], k = 1
 * 输出：[1,-1]
 * 示例 4：
 *
 * 输入：nums = [9,11], k = 2
 * 输出：[11]
 * 示例 5：
 *
 * 输入：nums = [4,-2], k = 2
 * 输出：[4]
 */
public class Solution239 {


    public static int[] maxSlidingWindow(int[] nums, int k) {
        int index = 0, right = 0;
        int[] res = new int[nums.length - k + 1];
        LinkedList<Integer> list = new LinkedList<>();
        // 构造窗口
        while (right < nums.length){
            // 将窗口中的小于nums
            while (!list.isEmpty() && nums[right] > list.getLast()) {
                //System.out.println(list.getLast());
                // 一、 1、3  1 移除
                list.removeLast();
            }
            list.addLast(nums[right]);
            // [1, 3, -1]
            right++; // 1 2 3
            if (right >= k){
                // 3 >= 3
                //System.out.println(list);
                res[index++] = list.getFirst();
                if (list.getFirst() == nums[right - k]){
                    list.removeFirst();
                }
            }

        }
        return res;
    }

    public static int[] maxSlidingWindow2(int[] nums, int k) {
        int right =0;
        int[] res = new int[nums.length -k +1];
        int index=0;
        LinkedList<Integer> list = new LinkedList<>();
        // 开始构造窗口
        while (right < nums.length) {
            // 这里的list的首位必须是窗口中最大的那位
            while (!list.isEmpty() && nums[right] > list.peekLast()) {
                list.removeLast();
            }
            // 不断添加
            list.addLast(nums[right]);
            right++;// 构造窗口完成，这时候需要根据条件做一些操作
            if (right >= k){
                res[index++]=list.peekFirst();
                System.out.println(list);
                // 如果发现第一个已经在窗口外面了，就移除
                if(list.peekFirst() == nums[right-k]) {
                    list.removeFirst();
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        System.out.println(Arrays.toString(maxSlidingWindow(nums, 3)));
//        System.out.println(Arrays.toString(maxSlidingWindow2(nums, 3)));
    }
}
