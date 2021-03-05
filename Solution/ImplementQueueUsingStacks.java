// site: https://leetcode-cn.com/problems/implement-queue-using-stacks/

// 用栈实现队列

class MyQueue {

    // 用两个栈实现队列
    private Deque<Integer> inStack;
    private Deque<Integer> outStack;

    /** Initialize your data structure here. */
    public MyQueue() {
        inStack = new LinkedList<Integer>();
        outStack = new LinkedList<Integer>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        // inStack栈存数据
        inStack.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        // 如果输出栈为空，将输入栈的内容倒到输出栈
        // 否则直接输出
        if(outStack.isEmpty()){
            inStack2outStack();
        }
        return outStack.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        // 跟pop类似，只是最后不用pop出来
        if(outStack.isEmpty()){
            inStack2outStack();
        }
        return outStack.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return ((inStack.isEmpty()) && (outStack.isEmpty()));
    }

    /**
    * 将输入栈的内容放到输出栈
    */
    private void inStack2outStack(){
        while(!inStack.isEmpty()){
            outStack.push(inStack.pop());
        }
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
