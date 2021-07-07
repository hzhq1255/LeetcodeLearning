package solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: hzhq1255
 * @Mail: hzhq1255@163.com
 * @Date: 2021/7/1 11:26 上午
 * @Desc: LCP 07. 传递信息
 * 小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：
 * <p>
 * 有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0
 * 每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。
 * 每轮信息必须需要传递给另一个人，且信息可重复经过同一个人
 * 给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。返回信息从小 A (编号 0 ) 经过 k 轮传递到编号为 n-1 的小伙伴处的方案数；若不能到达，返回 0。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 5, relation = [[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]], k = 3
 * <p>
 * 输出：3
 * <p>
 * 解释：信息从小 A 编号 0 处开始，经 3 轮传递，到达编号 4。共有 3 种方案，分别是 0->2->0->4， 0->2->1->4， 0->2->3->4。
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 3, relation = [[0,2],[2,1]], k = 2
 * <p>
 * 输出：0
 * <p>
 * 解释：信息不能从小 A 处经过 2 轮传递到编号 2
 * <p>
 * 限制：
 * <p>
 * 2 <= n <= 10
 * 1 <= k <= 5
 * 1 <= relation.length <= 90, 且 relation[i].length == 2
 * 0 <= relation[i][0],relation[i][1] < n 且 relation[i][0] != relation[i][1]
 */
public class SolutionLCP07 {

    /**
     * 5
     * [[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]]
     * 3
     */
    static int planNum = 0;
    static List<List<Integer>> routes = new LinkedList<>();

    public static int numWays(int n, int[][] relation, int k) {
        int[] starts = new int[1];
        int[] ends = new int[n];
        int plans = 0;
        while (k >= 1) {
            int count = 0;
            k--;
            System.out.println(k);
            for (int i = 0; i < relation.length; i++) {
                if (starts.length == 1){
                    if (starts[0] == relation[i][0]) {
                        ends[count] = relation[i][1];
                        count++;
                    }
                    continue;
                }
                for (int start : starts) {
                    if (start == relation[i][0]) {
                        ends[count] = relation[i][1];
                        count++;
                    }
                }
            }
            System.out.println(Arrays.toString(ends));
            starts = new int[count];
            for (int i = 0; i < count; i++){
                if (ends[i] == n - 1 && k == 0){
                    plans++;
                }
                starts[i] = ends[i];
            }
            ends = new int[ n * count];

        }
        return plans;
    }

//    public static void backTrace(int start, int end, int[][] relation, List<Integer> route) {
//
//        for(int i = 0; i < relation.length; i++){
//            if(relation[i][0] == start){
//                route.add(relation[i][1]);
//                if (relation[i][1] == end){
//                    return;
//                }
//                backTrace(relation[i][1], end, relation,route);
//            }
//            routes.add(route);
//        }
//    }



    public static int getPlans(int n, int[][] relation, int k){

        List<Integer> currentStarts = new ArrayList<>();
        List<Integer> currentEnds = new ArrayList<>();
        currentStarts.add(0);
        while (k >= 1){
            for (int i = 0; i < relation.length ; i++){
                final int index = i;
                currentStarts.forEach(integer -> {
                    if(integer.equals(relation[index][0])){
                        currentEnds.add(relation[index][1]);
                    }
                });
            }
            System.out.println();
            k--;
            currentStarts = List.copyOf(currentEnds);

            if (k != 0){
                currentEnds.clear();
            }
        }
        return (int) currentEnds.stream().filter(e -> e == n - 1).count();
    }

    public static void main(String[] args) {
        //[[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]]
        int[][] relation = {{0,2},{2,1},{3,4},{2,3},{1,4},{2,0},{0,4}};
//        3
//                [[0,1],[0,2],[2,1],[1,2],[1,0],[2,0]]
//        5
//        int[][] relation = {{0,1},{1,2},{1,0},{0,2}};
        int n = 5, k = 3;
//        System.out.println("plans=" +
//                numWays(3,
//                        new int[][]{{0,1},{0,2},{2,1},{1,2},{1,0},{2,0}},
//                        5));
        getPlans(3,new int[][]{{0,1},{0,2},{2,1},{1,2},{1,0},{2,0}},5);
//        numWays(n,relation,k);
    }

}
