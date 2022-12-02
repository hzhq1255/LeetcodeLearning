package org.example.leetcodelearning.solution;

import java.util.*;

/**
 * @Author: hzhq1255
 * @Mail: hzhq1255@163.com
 * @Date: 2021/6/2 9:20
 * @Desc:
 * 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 *
 * 输入：s = "cbbd"
 * 输出："bb"
 * 示例 3：
 *
 * 输入：s = "a"
 * 输出："a"
 * 示例 4：
 *
 * 输入：s = "ac"
 * 输出："a"
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母（大写和/或小写）组成
 */
public class Solution5 {
    public static void main(String[] args) {
        Solution5 solution = new Solution5();
        String[] tests = {
                "aaaaa",
//                "babad",
//                "cbbd",
//                "a",
//                "ac",

                  };
        for (String test: tests){
            String res = solution.longestPalindromeByDp(test);
            System.out.println(res);
        }
    }

    public String longestPalindrome(String s) {
        Map<Integer, List<String>> result = new HashMap<>();
        int maxLen = 0;
        for(int i = s.length() - 1; i >= 0; i--){
            List<String> sList = new ArrayList<>();
            for(int j = 0; j + i < s.length(); j++){

                String sub = s.substring(j,j+i+1);
                System.out.println(sub);
                int r = 0, l = sub.length() - 1;
                for(;r != l && r  != l +1 ; r++,l--){
                    System.out.println(r + " " + l);
                    if (sub.charAt(r) != sub.charAt(l)){
                        break;
                    }
                }
                if (r == l || r == l +1){
                    maxLen = Math.max(maxLen,i + 1);
                    sList.add(sub);
                }

            }
            if (sList.size() != 0){
                result.put(i, sList);
                break;
            }

        }
        System.out.println(result.toString());
        return result.get(maxLen-1).get(0);
    }

    public String longestPalindromeByForce(String s){
        int len = s.length();
        for (int i = len - 1; i >= 0; i--){
            for( int j = 0; j + i < len; j++){
                String sub = s.substring(j, j + i + 1);
                int right = 0, left = sub.length() - 1;
                while(right < left && sub.charAt(right) == sub.charAt(left)){
                    right++;
                    left--;
                }
                if (right >= left){
                    return sub;
                }
            }
        }
        return s;
    }


    public String longestPalindromeByExpand(String s){
        int center = 0,index = 0, len = s.length();
        int maxLen = 0;
        for(int i = 0; i < len; i++){
            int expandLen1 = expandCenter(s,i,i);
            int expandLen2 = expandCenter(s,i,i+1);
            int expandLen = 0;
            if (expandLen1 >= expandLen2){
                expandLen = expandLen1;
                center = 1;
            }else {
                expandLen = expandLen2;
                center = 2;
            }
            if (expandLen > maxLen){
                maxLen = expandLen;
                index = i;
            }
        }
        return s.substring(index - (maxLen - center) / 2, index + maxLen / 2 + center);
    }

    public int expandCenter(String s, int start, int end){
        int len = s == null ? 0 : s.length();
        while (start >= 0 && end <= len - 1){
            assert s != null;
            if ( s.charAt(start) != s.charAt(end) ){
                break;
            }
            start--;
            end++;
        }
        return end  - start  - 1;
    }

    // 动态规划
    public static String longestPalindromeByDp  (String s){
        int len = s.length(), maxlen = 1, begin = 0;
        if (len < 2){
            return s;
        }
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++){
            dp[i][i] = true;
        }
        for (int k = 2; k <= len; k++){
            for (int i = 0; i < len; i++){
                int j = k + i - 1;
                if (j >= len){
                    break;
                }
                if (s.charAt(i) != s.charAt(j)){
                    dp[i][j] = false;
                }else {
                    if (j - i < 3){
                        dp[i][j] = true;
                    }else {
                        dp[i][j] = dp[i+1][j+1];
                    }
                }
                if (dp[i][j] && j - i + 1 > maxlen){
                    maxlen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxlen);
    }

}
