// site: https://leetcode-cn.com/problems/palindrome-pairs/

// 根据回文串的特点
// 如果两个串 s1 + s2 可以构成回文串，那么根据s1和s2的长度len1和len2，有三种情况
// 1.len1 = len2，则 s1 是 s2 的逆序
// 2.len1 > len2，则 s1 可以分为 t1 和 t2，t1 是 s2 的逆序，t2 是一个回文串
// 3.len1 < len2，则 s2 可以分为 t1 和 t2，t2 是 s1 的逆序, t1 是一个回文串

// 令串 k 是两个串里面最长的， 对于每个 k 都枚举分割点j，将k分为 t1:[0, ..., j - 1]，t2: [j - 1, ..., m - 1]，长度为m
// 因为空串也是一个回文串，所以第一种情况可以看成是 null + k 或者 k + null，变成第二和第三种情况的特殊情况

// 需要一个数据结构来查询 [某个字符串的逆序] 是否存在，用字典树和哈希map都可以
// 用HashMap的
class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        int n = words.length;
        List<List<Integer>> res = new ArrayList<>();
        // key是构造回文需要的部分，value是下标
        HashMap<String, Integer> map = new HashMap<>();
        List<String> wordRev = new ArrayList<>();
        // 将word的逆序加入wordRev
        for(String word : words){
            wordRev.add(new StringBuilder(word).reverse().toString());
        }
        // 每个串的翻转加入map，方便后序的查找
        for(int i = 0; i < n; i++){
            map.put(wordRev.get(i), i);
        }
        // 遍历words
        for(int i = 0; i < n; i++){
            String word = words[i];
            // 获取每个串word(k)的长度
            int m = word.length();
            if(m == 0){
                continue;
            }
            // 枚举分割点j，将k分为t1和t2
            // j可以等于m，即将串k划分为 (null + k) 或者 (k + null)
            for(int j = 0; j <= m; j++){
                // t2部分(j，...，m-1)
                // 如果t2是一个回文，则(k + t1的逆序)可以构成回文串，即word为s1长的情况
                if(check(word, j, m - 1)){
                    // 在map中找t1部分的逆序，因为map里面存的是words里每个word的逆序
                    // 所以直接找t1(word[0, j - 1])就可以了
                    int s2Index = findWord(word, 0, j - 1, map);
                    // 因为之前把word[i]的逆序也放进去了，所以也要排除i
                    if (s2Index != -1 && s2Index != i){
                        // 找到了，放进结果
                        res.add(Arrays.asList(i, s2Index));
                    }
                }
                // 如果t1部分是回文
                if(j != 0 && check(word, 0, j - 1)){
                    // map找t2
                    int s1Index = findWord(word, j, m - 1, map);
                    if(s1Index != -1 && s1Index != i){
                        res.add(Arrays.asList(s1Index, i));
                    }
                }
            }
        }
        return res;
    }

    /**
     * 判断word从left到right是不是一个回文串
     * @param word 字符串
     * @param left 左下标
     * @param right 右下标
     * @return
     */
    public boolean check(String word, int left, int right){
        int len = right - left + 1;
        for(int i = 0; i < len / 2; i++){
            if(word.charAt(left + i) != word.charAt(right - i)){
                return false;
            }
        }
        return true;
    }

    /**
     * 在map里面找word[left, right]
     * @param word
     * @param left
     * @param right
     * @return 找到返回下标，找不到返回-1
     */
    public int findWord(String word, int left, int right, HashMap<String, Integer> map){
        return map.getOrDefault(word.substring(left, right + 1), -1);
    }
}

// 待添加字典树的做法
