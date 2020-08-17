// site: https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof//

// 最小栈的实现

// 另外一种写法，minStack不需要重复存放多次当前最小值，将其维护成一个dataStack的非严格降序子序列
// 关键在pop的时候，从dataStack中pop的时候，比较dataStack.peek()是不是等于minStack.peek()，是的话minStack也pop
// 比较的时候用equals比较好
class MinStack {

    // 一个存放数据，另一个存放最小值
    Deque<Integer> dataStack;
    Deque<Integer> minStack;

    /** initialize your data structure here. */
    public MinStack() {
        dataStack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }
    
    public void push(int x) {
        dataStack.addLast(x);
        // minStack为空或者当前x <= minStack.peek()才会加入
        if(minStack.isEmpty() || x <= minStack.getLast()){
            minStack.addLast(x);
        }
    }
    
    public void pop() {
        int temp = dataStack.removeLast();
        // 当弹出的数和minStack的peek即当前最小值相等的时候，才会弹出最小值
        if(temp == minStack.getLast()){
            minStack.removeLast();
        }
    }
    
    public int top() {
        return dataStack.getLast();
    }
    
    public int min() {
        return minStack.getLast();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.min();
 */

// 第一版的写法
// 用另外一个ArrayDeck来维护最小值，push的时候把最小值push进minStack，pop的时候一起pop
// 需要先再minStack里面存一个Integer.MAX_VALUE垫底
// 方便第一次min的比较
class MinStack {

    // 一个存放数据，另一个存放最小值
    Deque<Integer> dataStack = new ArrayDeque<>();
    Deque<Integer> minStack = new ArrayDeque<>();

    /** initialize your data structure here. */
    public MinStack() {
        minStack.addLast(Integer.MAX_VALUE);
    }
    
    public void push(int x) {
        dataStack.addLast(x);
        int min = minStack.getLast();
        // 和min比较谁更小，将较小者入栈
        if(x < min){
            minStack.addLast(x);
        }
        else{
            minStack.addLast(min);
        }
    }
    
    public void pop() {
        dataStack.removeLast();
        minStack.removeLast();
    }
    
    public int top() {
        return dataStack.getLast();
    }
    
    public int min() {
        return minStack.getLast();
    }
}
