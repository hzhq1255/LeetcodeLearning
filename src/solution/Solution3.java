package solution;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @Author: hzhq1255
 * @Mail: hzhq1255@163.com
 * @Date: 2021/5/22 22:49
 * @Desc: 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * 示例 4:
 * <p>
 * 输入: s = ""
 * 输出: 0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 */
public class Solution3 {
    public static void main(String[] args) {
        String test = "bbbb";
        String[] testArray = {""," ","bbb","abc","abcabcbb","bbbbb","pwwkew"};
        Solution3 solution = new Solution3();
        for (String s: testArray){
            System.out.println(solution.test(s));
        }
    }

    public int test(String s){
        HashSet<Character> hashSet = new HashSet<>();
        int len = s.length();
        int right = -1, result = 0;
        for (int i = 0; i < len ; i++){
            if (i != 0){
                hashSet.remove(s.charAt(i - 1));
            }
            while (right + 1 < len && !hashSet.contains(s.charAt(right+1))){
                hashSet.add(s.charAt(right+1));
                right++;
            }
            result = Math.max(right+1-i,result);
        }
        return result;
    }

    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int k = 0;
        int[] result = new int[len];
        Arrays.fill(result, 0);
        while (k <= len){
            k++;
            int offset = len % k;
            for (int index = 0 ; index <= offset; index ++){
                for (int i = index; i + k <= len; i++){
                    String sub = s.substring(i,i+k);
                    if (isNoRepeatString(sub)){
                        result[k - 1]++;
                    }
                }
            }
        }
        return getMaxLen(result);
    }

    public boolean isNoRepeatString(String s) {
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getMaxLen(int[] result){
        int len = 0;
        for(int i = result.length - 1; i >= 0; i--){
            if (result[i] != 0){
                len = i + 1;
                break;
            }
        }
        return len;
    }

    public void printIntArray(int[] intArray) {
        int len = intArray.length;
        for (int i = 0; i < len; i++) {
            System.out.println("index: " + i + " value: " + intArray[i]);
        }
    }

}
