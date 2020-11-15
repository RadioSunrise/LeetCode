// site: https://leetcode-cn.com/problems/remove-k-digits/

// 删除数字中的k位，使剩余的数值最小，先用栈来保存遍历的数字，用贪心的思想来做
// 整体的想法是希望一个数字按照每一位递增的方式来排列，这样的结果是最小的，所以要删去一些递减的情况（通过删除大的栈顶）
// 判断遍历的数字和栈顶元素之间的大小，如果栈顶的元素大，说明加入当前原之后会有递减，因此需要弹出
// 有可能后面会有更大的数字出现递减，但按照顺序遍历的做法，先出现的递减影响更大，因此先删去

class Solution {
    public String removeKdigits(String num, int k) {
        int n = num.length();
        if(n <= k){
            return "0";
        }
        // 用栈来存储当前遍历的数字
        // 如果比当前元素比栈顶元素比小，栈顶出栈
        Stack<Character> stack = new Stack<>();
        int popNum = 0;
        for(char c : num.toCharArray()){
            while(popNum < k && !stack.isEmpty() && c < stack.peek()){
                popNum++;
                stack.pop();
            }
            // 处理前导0，只有当c = 0且stack为空才不入栈
            if(c != '0' || !stack.isEmpty()){
                stack.push(c);
            }
        }
        // 遍历完了都没有弹出足够的数量，此时num整体递增，砍去末尾
        while(popNum < k && !stack.isEmpty()){
            popNum++;
            stack.pop();
        }
        // 剩下的数字反序转回字符串
        StringBuffer res = new StringBuffer();
        while(!stack.isEmpty()){
            res.append(stack.pop());
        }
        // 额外判断栈为空的情况
        if(res.length() == 0){
            return "0";
        }
        else{
            return res.reverse().toString();
        }
    }
}
