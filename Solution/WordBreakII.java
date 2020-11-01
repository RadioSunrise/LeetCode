package leetcode;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/word-break-ii/
 * 单词拆分2，单词拆分1是判读能否拆分，这次要给出拆分的结果，包含所有可能性
 * 用回溯法来做，指定start作为起始分割点，从枚举本层的单词结束点i，如果[start, i - 1]是一个word
 * 则进入下一层搜索，所以dfs的返回值设计成List<String>，本层的结果和下一层合并
 * 由于会有大量的重复搜索同一个start的情况，所以要做记忆化用memo来记录每个start可以划分的结果
 */
public class WordBreakII {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        if(s == null){
            return res;
        }
        Set<String> dictionary = new HashSet<>(wordDict);
        // 记忆化memo，key是start，value是分割结果List<String>
        Map<Integer, List<String>> memo = new HashMap<>();
        // 递归
        return dfs(s, 0, dictionary, memo);
    }

    /**
     * 递归函数dfs，返回的是从s从start开始的分割结果
     * @param s
     * @param start
     * @param dictionary
     * @return
     */
    public List<String> dfs(String s, int start, Set<String> dictionary, Map<Integer, List<String>> memo){
        // 如果已经有结果了，则不用求了，返回已知的分割结果
        if(memo.containsKey(start)){
            return memo.get(start);
        }
        // 结束条件，整个串都分割完成，返回有一个空元素的list
        if(start >= s.length()){
            List<String> temp = new ArrayList<>();
            // 一定要加一个""字符，否则在最后一个单词合并结果的时候得到一个空的next，当成不能分割
            temp.add("");
            return temp;
        }
        // 从start开始的分割结果
        List<String> res = new ArrayList<>();
        // 枚举结束点，从[start : i - 1]是不是一个word
        for(int i = start + 1; i <= s.length(); i++){
            String sub = s.substring(start, i);
            // 如果是一个word
            if(dictionary.contains(sub)){
                // 递归，从i开始作为分割起点
                List<String> next = dfs(s, i, dictionary, memo);
                // sub和next中的串逐个合并结果
                // 如果next为空，则不会执行这个for循环
                // 因此res中也不会加入当前单词，只有当后续部分都分割完了，next才会有元素
                for(String str : next){
                    res.add(str.equals("")? sub : sub + " " + str);
                }
            }
        }
        // 加入memo
        memo.put(start, res);
        return res;
    }
    public static void main(String[] args){
        String s = "catsanddog";
        List<String> wordD = new ArrayList<>();
        wordD.add("cat");
        wordD.add("cats");
        wordD.add("and");
        wordD.add("sand");
        wordD.add("dog");
        List<String> res = new WordBreakII().wordBreak(s, wordD);
        System.out.println(res.toString());
    }
}
