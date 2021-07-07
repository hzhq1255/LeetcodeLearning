package solution;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: hzhq1255
 * @Mail: hzhq1255@163.com
 * @Date: 2021/7/6 9:57 上午
 * @Desc:
 * 1418. 点菜展示表
 * 给你一个数组 orders，表示客户在餐厅中完成的订单，确切地说， orders[i]=[customerNamei,tableNumberi,foodItemi] ，其中 customerNamei 是客户的姓名，tableNumberi 是客户所在餐桌的桌号，而 foodItemi 是客户点的餐品名称。
 *
 * 请你返回该餐厅的 点菜展示表 。在这张表中，表中第一行为标题，其第一列为餐桌桌号 “Table” ，后面每一列都是按字母顺序排列的餐品名称。接下来每一行中的项则表示每张餐桌订购的相应餐品数量，第一列应当填对应的桌号，后面依次填写下单的餐品数量。
 *
 * 注意：客户姓名不是点菜展示表的一部分。此外，表中的数据行应该按餐桌桌号升序排列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：orders = [["David","3","Ceviche"],["Corina","10","Beef Burrito"],["David","3","Fried Chicken"],["Carla","5","Water"],["Carla","5","Ceviche"],["Rous","3","Ceviche"]]
 * 输出：[["Table","Beef Burrito","Ceviche","Fried Chicken","Water"],["3","0","2","1","0"],["5","0","1","0","1"],["10","1","0","0","0"]]
 * 解释：
 * 点菜展示表如下所示：
 * Table,Beef Burrito,Ceviche,Fried Chicken,Water
 * 3    ,0           ,2      ,1            ,0
 * 5    ,0           ,1      ,0            ,1
 * 10   ,1           ,0      ,0            ,0
 * 对于餐桌 3：David 点了 "Ceviche" 和 "Fried Chicken"，而 Rous 点了 "Ceviche"
 * 而餐桌 5：Carla 点了 "Water" 和 "Ceviche"
 * 餐桌 10：Corina 点了 "Beef Burrito"
 * 示例 2：
 *
 * 输入：orders = [["James","12","Fried Chicken"],["Ratesh","12","Fried Chicken"],["Amadeus","12","Fried Chicken"],["Adam","1","Canadian Waffles"],["Brianna","1","Canadian Waffles"]]
 * 输出：[["Table","Canadian Waffles","Fried Chicken"],["1","2","0"],["12","0","3"]]
 * 解释：
 * 对于餐桌 1：Adam 和 Brianna 都点了 "Canadian Waffles"
 * 而餐桌 12：James, Ratesh 和 Amadeus 都点了 "Fried Chicken"
 * 示例 3：
 *
 * 输入：orders = [["Laura","2","Bean Burrito"],["Jhon","2","Beef Burrito"],["Melissa","2","Soda"]]
 * 输出：[["Table","Bean Burrito","Beef Burrito","Soda"],["2","1","1","1"]]
 *
 *
 * 提示：
 *
 * 1 <= orders.length <= 5 * 10^4
 * orders[i].length == 3
 * 1 <= customerNamei.length, foodItemi.length <= 20
 * customerNamei 和 foodItemi 由大小写英文字母及空格字符 ' ' 组成。
 * tableNumberi 是 1 到 500 范围内的整数。
 */
public class Solution1418 {

    public static void main(String[] args) {

    }


    public static List<List<String>> displayTable(List<List<String>> orders){
        Map<String,Integer> orderMap = new HashMap<>();
        Set<String> labelSet = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        Set<String> tableSet = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.valueOf(o1).compareTo(Integer.valueOf(o2));
            }
        });
        for (List<String> order : orders){
            //System.out.println(order);

            labelSet.add(order.get(2));
            tableSet.add(order.get(1));
            String key = order.get(1) + "," + order.get(2);
            if (orderMap.get(key) == null){
                orderMap.put(key,1);
            }else {
                orderMap.put(key,orderMap.get(key) + 1);
            }
        }
        System.out.println(labelSet);
        System.out.println(tableSet);
        System.out.println(orderMap);
        List<List<String>> result = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        labels.add("Table");
        labels.addAll(labelSet);
        result.add(labels);
        for(String table: tableSet){
            List<String> orderShowList = new ArrayList<>();
            orderShowList.add(table);
            for(String label: labelSet){
                String key = table+","+label;
                if (orderMap.get(key) == null){
                    orderShowList.add("0");
                } else {
                    orderShowList.add(orderMap.get(key).toString());
                }
            }
            result.add(orderShowList);
        }
        return result;

    }

    public static List<List<String>> displayTable1(List<List<String>> orders){
        Map<Integer,Map<String, Integer>> orderMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        List<List<String>> orderShowTable = new ArrayList<>();
        Set<String> head = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        for (List<String> order: orders){
            Map<String, Integer> foodNums = new HashMap<>();
            Integer key = Integer.valueOf(order.get(1));
            String foodKey = order.get(2);
            head.add(foodKey);
            if (orderMap.get(key) == null){
                foodNums.put(foodKey,1);
                orderMap.put(key,foodNums);
            }else {
                foodNums = orderMap.get(key);
                if (foodNums.get(foodKey) == null){
                    foodNums.put(foodKey,1);
                }else {
                    foodNums.put(foodKey,foodNums.get(foodKey) + 1 );
                }
            }
        }
        System.out.println(orderMap);
        Map<String, Integer> headMap = new TreeMap<>();
        int count = 1;
        for (String s: head){
            headMap.put(s,count);
            count++;
        }
        List<String> headList = new ArrayList<>();
        headList.add("Table");
        headList.addAll(head);
        orderShowTable.add(headList);
        System.out.println(headMap);
        for (Integer key: orderMap.keySet()){
            List<String> list = new ArrayList<>(Collections.nCopies(headList.size(),"0"));
            list.set(0,key.toString());
            Map<String, Integer> foodMap = orderMap.get(key);
            for (String foodKey:foodMap.keySet()){
                int index = headMap.get(foodKey);
                list.set(index,foodMap.get(foodKey).toString());
            }
            orderShowTable.add(list);
        }
        return orderShowTable;
    }
}
