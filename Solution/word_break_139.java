package leetcode;

import java.util.List;

public class word_break_139 {
    // DFS bad，solution_DFS_bad
    // 没有考虑剪枝和重复计算的问题，会超时
    class solution_DFS_bad
    {
        public boolean wordBreak(String s, List<String> wordDict)
        {
            return dfs(s, 0, wordDict);
        }
        public boolean dfs(String s, int start, List<String> wordDict)
        {
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
}
