package solution;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: hzhq
 * @date 2021/8/11 11:44 下午
 * @desc:
 * 217. 存在重复元素
 * 给定一个整数数组，判断是否存在重复元素。
 *
 * 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [1,2,3,1]
 * 输出: true
 * 示例 2:
 *
 * 输入: [1,2,3,4]
 * 输出: false
 * 示例 3:
 *
 * 输入: [1,1,1,3,3,4,3,2,4,2]
 * 输出: true
 */
public class Solution217 {

    // 排序
    public boolean containsDuplicate1(int[] nums){
        // 1,1,1,2,2,3
        Arrays.sort(nums);
        int temp = 0, count = 0;
        for (int num: nums){
            if (count == 0){
                temp = num;
            } else {
                if (temp == num){
                    return true;
                }
                temp = num;
            }
            count++;
        }
        return false;
    }

    // 哈希 hashMap
    public boolean containsDuplicate2(int[] nums){
        // 1,1,1,2,2,3
        HashMap<Integer,Integer> cntMap = new HashMap<>();
        for (int num: nums){
            if (cntMap.containsKey(num)){
                if (cntMap.get(num) == 1){
                    return true;
                }
            } else {
                cntMap.put(num,1);
            }
        }
        return false;
    }

    // 哈希 hashSet
    public boolean containsDuplicate3(int[] nums){
        Set<Integer> numSet = new HashSet<>();
        for (int num: nums){
            if (numSet.contains(num)){
                return true;
            }else {
                numSet.add(num);
            }
        }
        return false;
    }

    }
