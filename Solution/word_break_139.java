package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class word_break_139 {
    // DFS bad，solution_DFS_bad
    // 没有考虑剪枝和重复计算的问题，会超时
    static class solution_DFS_bad
    {
        public static boolean wordBreak(String s, List<String> wordDict)
        {
            return dfs(s, 0, wordDict);
        }
        public static boolean dfs(String s, int start, List<String> wordDict)
        {
            System.out.println("Now start point is " + start);
            // start开始位置
            // Dict中的每个word都遍历一遍
            for(String word : wordDict)
            {
                // 计算下一个递归的开始位置
                int next_start = start + word.length();
                // word长度超过当前剩余的内容 即 下一个递归起点位置超过了长度
                if(next_start > s.length())
                {
                    continue; // 遍历下一个word
                }
                // 如果start位置就是word的起点，即s从当前位置包含word
                if(s.indexOf(word, start) == start)
                {
                    // 包含word的情况下如果到尾部了就返回true
                    // 从next_start开始递归，如果结果为true也返回true
                    if(next_start == s.length() || dfs(s, next_start, wordDict))
                    {
                        System.out.println("start is " + start + ", and " + word + " is ok");
                        return true;
                    }
                }
            }
            // 如果从这个位置开始所以的word都不行，返回false
            return false;
        }
    }

    // DFS cutting，solution_DFS_cutting
    // 增加一个visited数组，减少重复访问同一个start
    static class solution_DFS_cutting
    {
        public static boolean wordBreak(String s, List<String> wordDict)
        {
            // 新增visited，长度是length+1，包含刚刚好到s的尾部+1的位置
            boolean[] visited = new boolean[s.length()+1];
            return dfs(s, 0, wordDict, visited);
        }
        public static boolean dfs(String s, int start, List<String> wordDict, boolean[] visited)
        {
            System.out.println("Now start point is " + start);
            // start开始位置
            // Dict中的每个word都遍历一遍
            for(String word : wordDict)
            {
                // 计算下一个递归的开始位置
                int next_start = start + word.length();
                // word长度超过当前剩余的内容 即 下一个递归起点位置超过了长度
                // 或者是从next_start位置递归的判断已经做过了(是不行的)
                if(next_start > s.length() || visited[next_start])
                {   //必须要大于s.length，否则到next_start指向末尾+1的位置会不进行判断直接continue

                    continue; // 遍历下一个word
                }
                // 如果start位置就是word的起点，即s从当前位置包含word
                if(s.indexOf(word, start) == start)
                {
                    // 包含word的情况下如果到尾部了就返回true
                    // 从next_start开始递归，如果结果为true也返回true
                    if(next_start == s.length() || dfs(s, next_start, wordDict, visited))
                    {
                        // System.out.println("start is " + start + ", and " + word + " is ok");
                        return true;
                    }
                    // 能到这一步说明从next_start开始的遍历已经做过了，不用重复做判断
                    visited[next_start] = true;
                }
            }
            // 如果从这个位置开始所以的word都不行，返回false
            return false;
        }
    }
    // 测试用main
    public static void main(String[] args)
    {
        // 用例1
        String s = "applepenapple";
        List<String> wordD = new ArrayList<String>();
        wordD.add("apple");
        wordD.add("pen");

        // 用例2
//        String s= "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
//        List<String> wordD = Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa");
        System.out.println(s.length());
        System.out.println(solution_DFS_cutting.wordBreak(s,wordD));
    }
}
