// site: https://leetcode-cn.com/problems/partition-labels/

// 划分字符

// 贪心法 + 双指针实现
// 先统计每个字符最后出现的位置，当遇到一个字符的时候，从这个字符到它最后出现的全部内容一定在同一个区间内
// 在遍历的过程中判断是否需要扩展区间（用end指定当前区间的结尾）
// 当i遇到end了，说明这[start:end]这一区间的字符已经全部划分完毕，更新start = end + 1（下一个位置开始）

class Solution {
    public List<Integer> partitionLabels(String S) {
        int[] last = new int[26];
        for(int i = 0; i < S.length(); i++){
            char c = S.charAt(i);
            last[(int)(c - 'a')] = i;
        }
        List<Integer> res = new LinkedList<>();
        int start = 0;
        int end = 0;
        for(int i = 0; i < S.length(); i++){
            // 更新end
            end = Math.max(end, last[(S.charAt(i) - 'a')]);
            // i走到end了
            if(i == end){
                res.add(end - start + 1);
                // 更新start，从end的下一个位置开始
                start = end + 1;
            }
        }
        return res;
    }
}
