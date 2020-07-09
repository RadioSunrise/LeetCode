package leetcode;

import java.util.*;

public class word_break_139 {
    
    // dp
    // dp[i]表示从s[0, ..., i-1]是否可以分割，boolean
    // dp[i]为true的条件是，考虑s[0, ..., i-1]的分割点j，s[0, ..., j-1] 和 s[j, ..., i-1]可以被分割
    // 即dp[j] == true且s[j, ..., i-1]是一个词
    class Solution_dp {
        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> wordDictSet = new HashSet<>();
            int max_word_len = 0;
            for(String word : wordDict){
                wordDictSet.add(word);
                if(word.length() > max_word_len)
                {
                    max_word_len = word.length();
                }
            }
            boolean[] dp = new boolean[s.length() + 1];
            dp[0] = true;
            for(int i = 1; i < dp.length; i++){
                // 优化剪枝，j从尾部开始，判断到最长的单词长度的位置就可以了
                for(int j = i; j >= 0 && j >= i - max_word_len; j--){
                    // dp[i]是[0,...,i-1]
                    // 用HashSet来判断s[j, ..., i-1]是不是一个词
                    if(dp[j] && wordDictSet.contains(s.substring(j,i))){
                        dp[i] = true;
                    }
                }
            }
            return (dp[s.length()]);
        }
    }


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

    // BFS搜索
    static class solution_BFS
    {
        public static boolean wordBreak(String s, List<String> wordDict)
        {
            return bfs(s, wordDict);
        }
        public static boolean bfs(String s, List<String> wordDict)
        {
            // 创建队列
            Queue<Integer> q = new LinkedList<>();
            // visited数组
            boolean[] visited = new boolean[s.length() + 1];
            q.add(0);
            while(! q.isEmpty())
            {
                int start = q.poll();
                System.out.println("Now start is " + start);
                // visited[start] = true;
                for (String word : wordDict)
                {
                    int next_start = start + word.length();
                    if(next_start > s.length() || visited[next_start])
                    {
                        continue;
                    }
                    if(s.indexOf(word, start) == start) //从start位置可以匹配到word
                    {
                        if(next_start == s.length())
                        {
                            return true;
                        }
                        q.add(next_start);
                        visited[next_start] = true; //修改访问
                    }
                }
            }
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

        // 用例3
//        String s = "catsandog";
//        List<String> wordD = Arrays.asList("cats","dog","sand","and","cat");

        System.out.println(s.length());
        System.out.println(solution_BFS.wordBreak(s,wordD));
    }
}
