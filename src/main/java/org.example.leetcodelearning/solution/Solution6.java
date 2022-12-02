package org.example.leetcodelearning.solution;

/**
 * @author: hzhq
 * @date 2021/7/16 3:17 下午
 * @desc: 6. Z 字形变换
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * <p>
 * string convert(string s, int numRows);
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * 示例 3：
 * <p>
 * 输入：s = "A", numRows = 1
 * 输出："A"
 */
public class Solution6 {

    public static String convert(String s, int numRows) {
        StringBuilder builder = new StringBuilder();
        if (numRows <= 1){
            return s;
        }
        for (int i = 0; i < numRows; i++) {
            System.out.println(builder.toString());
            System.out.println(i);
//            if (i == 0) {
//                for (int j = i; j < s.length(); j += 2 * (numRows - 1)) {
//                    builder.append(s.charAt(j));
//                    System.out.println(j + " " + s.charAt(j));
//
//                }
//            }
//            if (i > 0 && i < numRows / 2) {
//                for (int j = i; j < s.length(); j += 2 * (numRows - i - 1)) {
//                    if (j == i) {
//                        builder.append(s.charAt(j));
//                        System.out.println(j + " " + s.charAt(j));
//                    } else {
//                        builder.append(s.charAt(j));
//                        System.out.println(j + " " + s.charAt(j));
//                        j = j + i + 1;
//                        if (j  < s.length()){
//                            builder.append(s.charAt(j ));
//                            System.out.println(j + " " + s.charAt(j));
//                        }
//                    }
//                }
//            }

            if (i >= numRows / 2 && i < numRows - 1) {
                if (i == numRows / 2 && numRows % 2 == 1) {
                    for (int j = i; j < s.length(); j += 2 * (numRows - i - 1)) {
                        builder.append(s.charAt(j));
                        System.out.println(j + " " + s.charAt(j));
                    }
                } else {
                    for (int j = i; j < s.length(); j += 2 * i) {
                        builder.append(s.charAt(j));
                        System.out.println(j + " " + s.charAt(j));
                        j = j + numRows - i;
                        if ( j < s.length()){
                            builder.append(s.charAt(j));
                            System.out.println(j + " " + s.charAt(j));
                        }
                    }
                }

            }
//            if (i == numRows - 1) {
//                for (int j = i; j < s.length(); j += 2 * i) {
//                    builder.append(s.charAt(j));
//                }
//            }

        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String test = "PAYPALISHIRING";
        System.out.println(convert(test, 6));
    }
}
