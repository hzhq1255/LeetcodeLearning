package solution;

import java.util.Arrays;

/**
 * 829. 连续整数求和
 * 给定一个正整数 n，返回 连续正整数满足所有数字之和为 n的组数 。
 * 示例 1:
 * 
 *  输入: n = 5
 *  输出: 2
 *  解释: 5 = 2 + 3，共有两组连续整数([5],[2,3])求和后为 5。
 *  示例 2:
 * 
 *  输入: n = 9
 *  输出: 3
 *  解释: 9 = 4 + 5 = 2 + 3 + 4
 *  示例 3:
 *  输入: n = 15
 *  输出: 4
 *  解释: 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
 *  
 */
public class Solution892 {


    private static int N_5 = 5;

    private static int N_9 = 9;

    private static int N_15 = 15;

    /**
     * 暴力循环匹配
     * (n + m )*(m -n + 1) / 2
     * @param number 正整数
     * @return 组数
     */
    private static int consecutiveNumbersSumMethod1(int number){
        // 找出中位数
        int mid = (int) (((double) number /2) + 1);
        // 连续数字 计算公式 n <= m
        // (n + m )*(m -n + 1) / 2
        //  （2 + 3) / 2 * 3
        int count = 0;
        int sum = 0;
        int[][] groups = new int[mid][number];
        for (int i = mid; i >= 1 && number != 1 && number != 2; i-- ){
            sum = i;
            groups[mid - i][0] = i;
            for (int j = i - 1; j >= 0 ; j-- ){
                groups[mid-i][i -j]= j;
                sum += j;
                if (sum == number){
                    count += 1;
                    System.out.println(Arrays.toString(groups[mid - i]));
                    break;
                }
            }
        }
//        System.out.println(Arrays.deepToString(groups));
        return count + 1;
    }

    /**
     * 通过数学公式推导
     * 设连续数字起始值为 a 有k项 结束值 a + k - 1
     * 由等差数列公式得到和  ( 2*a + k - 1) * k / 2 =
     * 通过公式推导得到
     * 2n / k -k + 1 = 2a >= 2
     * 2n /k >= k + 1 => 2n / k > k
     * 所以 k 在 （1,√2n) 之间
     * 并符合 (2 * a + k - 1) * k = 2* n 即  2 * n % k 为正整数
     * @param n 数字
     * @return ans
     */
    public static int consecutiveNumbersSumMethod2(int n){

        int ans = 0;

        for (int k = 1; k * k <= 2 * n; k++ ){
            if ( (2 * n) % k != 0){
                continue;
            }
            if ( ((2 * n/ k - k + 1 ) ) % 2 == 0 ){
                ans++;
            }
        }
        return ans;
    }


    
    public static void main(String[] args) {
//        int n = 9;
//        System.out.println((int) (((double) 1 /2) + 1));
//
//        System.out.println(consecutiveNumbersSumMethod1(N_5));
        System.out.println(consecutiveNumbersSumMethod1(49));
        System.out.println(consecutiveNumbersSumMethod1(15*3));
//        for (int i = 1; i <= 10; i++){
//            System.out.println( 2 << i);
//            System.out.println(  consecutiveNumbersSumMethod1(2 << i));
//        }
        long strat = System.currentTimeMillis() / 1000;
        System.out.println("start time: " + strat);
        for (int i = 1; i <= Integer.MAX_VALUE; i++){
//            int count = consecutiveNumbersSumMethod1(i);
            System.out.println("i=" + i + " , count=" + consecutiveNumbersSumMethod2(i));
        }
        long end = System.currentTimeMillis() / 1000;
        System.out.println("end time: " + end);
        System.out.println("spend time: " + (end - strat));
    }
}
