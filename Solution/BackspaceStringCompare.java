// site: https://leetcode-cn.com/problems/backspace-string-compare/

// 比较两个含退格字符的字符串最后是不是相等

// 可以用栈构造出两个串的最终结果，比较结果即可
// 也可以用双指针的方法做

// 用双指针实现，因为退格是删除前面的字符的，所以从后开始遍历，用一个skip计数统计当前要删除的字符，遇到非退格符且skip等于0则说明这个字符要留下来，和另一个字符串要留下来的字符比较即可
// 关键细节是退出的判断和条件判断，可能一个走到头了另一个没走到头，这也是不可以的
class Solution {
    public boolean backspaceCompare(String S, String T) {
        // 双指针实现
        int sPoint = S.length() - 1;
        int tPoint = T.length() - 1;
        int skipS = 0;
        int skipT = 0;
        
        // 从后开始遍历指针
        while(sPoint >= 0 || tPoint >= 0){
            // 一个字符串指针先移动
            while(sPoint >= 0){
                if(S.charAt(sPoint) == '#'){
                    skipS++;
                    sPoint--;
                }
                // skip大于0，说明这个字是要删除的
                else if (skipS > 0){
                    skipS--;
                    sPoint--;
                }
                // skip <= 0 说明这个字符是要留下来的，跳出本字符串的循环，和另一个串的字符比较
                else {
                    break;
                }
            }
            while(tPoint >= 0){
                if(T.charAt(tPoint) == '#'){
                    skipT++;
                    tPoint--;
                }
                else if (skipT > 0){
                    skipT--;
                    tPoint--;
                }
                else {
                    break;
                }
            }
            // 比较两个留下的字符
            if(sPoint >= 0  && tPoint >=0){
                if(S.charAt(sPoint) != T.charAt(tPoint)){
                    return false;
                }
            }
            else {
                // 如果两个指针都超过头(小于0)了是可以的，如果只有一个到头了
                if(sPoint >= 0 || tPoint >= 0){
                    return false;
                }
            }
            // 走到这里说明两个字符是相等的或者，或者两个指针都小于0
            sPoint--;
            tPoint--;
        }
        return true;
    }
}
