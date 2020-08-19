// site: https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/submissions/

// 给定一个入栈序列，判断出栈序列是否合法

// 栈模拟法，按照入栈顺序入栈，用一个下标指示popped序列中的下标
// 入栈序列中每个数字入栈之后，比较栈顶元素和popped[ptr]是否相同，如果相同，则ptr前移
// 最后判断ptr是否到结尾n
// 时间和空间复杂度都是O(n)
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        // 指示popped序列的指针ptr
        int ptr = 0;
        int n = popped.length;
        if(n == 0){
            return true;
        }
        // 辅助栈
        Stack<Integer> stack = new Stack<>();
        for(int num : pushed){
            stack.push(num);
            // 当peek和popped(ptr)相等，则pop，且ptr前移，所以是循环
            while(!stack.isEmpty() && stack.peek() == popped[ptr]){
                // System.out.println("ptr is " + ptr);
                // System.out.println("Same, " + stack.peek() + ", and popped[ptr] is " + popped[ptr]);
                stack.pop();
                ptr++;
            }
        }
        // pushed序列都压入过栈了
        return (ptr == n);
    }
}

// 原地修改法
// 把pushed数组当成栈来用
// 多一个指针i用来指示pushed中放元素的位置
// 注意退出while循环之后要i++，消除最后一次变化
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        // 指示popped序列的指针ptr
        int ptr = 0;
        int i = 0;
        int n = popped.length;
        if(n == 0){
            return true;
        }
        // 辅助栈 -- 用pushed当栈用
        for(int num : pushed){
            pushed[i] = num;
            // 当peek和popped(ptr)相等，则pop，且ptr前移，所以是循环
            while(i >=0 && pushed[i] == popped[ptr]){
                // 出栈则i--
                i--;
                ptr++;
            }
            // 退出循环的时候i--过，要么是小于0，要么是不相等，所以i要i++消除最后一次变化
            i++;
        }
        // pushed序列都压入过栈了
        return (ptr == n);
    }
}
