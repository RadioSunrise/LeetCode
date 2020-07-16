// site: https://leetcode-cn.com/problems/min-stack/

// 实现最小栈，主要还是getMin的问题
// 用辅助栈来实现
// 关键思想是最小值可以存很多次的，弹出最小值也还有保留着最小值(之前的最小值也留在minStack里面)

class MinStack {

    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;

    /** initialize your data structure here. */
    public MinStack() {
        dataStack = new Stack<>();
        minStack = new Stack<>();
        // minStack先压入一个大数，用来比较
        minStack.push(Integer.MAX_VALUE);
    }
    // 辅助栈和数据栈同步压入和弹出
    // 压入的是当前最小的元素
    public void push(int x) {
        dataStack.push(x);
        // 因为初始化的时候有压入一个大数，所以不会空
        minStack.push(Math.min(minStack.peek(), x));
    }
    
    public void pop() {
        dataStack.pop();
        minStack.pop();
    }
    
    public int top() {
        return dataStack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
