package org.example.leetcodelearning.solution;

import java.util.*;
/**
 * @Author: hzhq1255
 * @Mail: hzhq1255@163.com
 * @Date: 2021/5/21 23:55
 * @Desc:
 *
 *
 *     692. 前K个高频单词
 */
public class Solution692 {
    public void sort(Map<String,Integer> map){
        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                int sortInt = o2.getValue().compareTo(o1.getValue());
                return sortInt == 0 ? o1.getKey().compareTo(o2.getKey()) : sortInt;
            }
        });
        map.clear();
        for (Map.Entry<String, Integer> mapping : list){
            // System.out.println(mapping.getKey()+": "+mapping.getValue());
            map.put(mapping.getKey(), mapping.getValue());
        }
    }

    public List<String> topKFrequent_1(String[] words, int k) {
        Map<String,Integer> wordCount = new LinkedHashMap<>();
        for(String word: words){
            if(wordCount.get(word) == null){
                wordCount.put(word,1);
            }else{
                int count = wordCount.get(word);
                wordCount.put(word,count+1);
            }
        }
        sort(wordCount);
        List<String> result = new ArrayList();
        int index = 0;
        for(String key: wordCount.keySet()){
            if(index < k){
                result.add(key);
            }else{
                break;
            }
            index++;
        }
        //    System.out.println(result.toString());
        return result;
    }

    public List<String> topKFrequent_2(String[] words, int k){
        return null;
    }

    public static void main(String[] args) {
        String[] test1 = {"i", "love", "leetcode", "i", "love", "coding"};
        String[] test2 = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
    }
}
