// site: https://leetcode-cn.com/problems/flatten-nested-list-iterator/

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

// 扁平化嵌套队列

class NestedIterator2 implements Iterator<Integer> {

    /**
    * 实现2
    * 用栈代替递归，不提前展开存储，而是遇到 next() 的时候再遍历
    * 先逆序把 nestedList 的元素塞到栈里面
    */ 
    private Deque<NestedInteger> stack = new ArrayDeque<>();

    public NestedIterator(List<NestedInteger> nestedList) {
        // 逆序塞到栈里面
        for(int i = nestedList.size() - 1; i >= 0; i--){
            NestedInteger n = nestedList.get(i);
            stack.addLast(n);
        }
    }

    @Override
    public Integer next() {
        // 栈里面弹出一个整数
        return stack.pollLast().getInteger();
    }

    @Override
    public boolean hasNext() {
        // 先判断栈空
        if(stack.isEmpty()){
            return false;
        } else {
            // 判断栈顶元素是否为整数，如果是整数返回true
            // 是嵌套列表则展开，逆序存入栈
            NestedInteger curr = stack.peekLast();
            if(curr.isInteger()){
                return true;
            } else {
                curr = stack.pollLast();
                List<NestedInteger> list = curr.getList();
                // 逆序入栈
                for(int i = list.size() - 1; i >= 0; i--){
                    stack.addLast(list.get(i));
                }
                // 继续递归 hasNext()，直到有整数可以返回为止
                //如果直接返回false，则代表没有下一个值，后续将停止
                return hasNext();
            }
        }
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */

class NestedIterator1 implements Iterator<Integer> {

    /**
    * 实现1
    * 先初始化好，遍历整个 nestedList ，存到一个队列里面
    */ 
    private Queue<Integer> queue;

    public NestedIterator(List<NestedInteger> nestedList) {
        queue = new LinkedList<>();
        // dfs 遍历
        DFS(nestedList);
    }

    // 深度优先遍历
    private void DFS(List<NestedInteger> nestedList){
        for(NestedInteger curr : nestedList){
            // 如果是一个整数，加入队列
            if(curr.isInteger()){
                queue.offer(curr.getInteger());
            } else {
                // 如果是一个嵌套列表，dfs 递归遍历
                DFS(curr.getList());
            }
        }
    }

    @Override
    public Integer next() {
        // 队列出队
        return queue.poll();
    }

    @Override
    public boolean hasNext() {
        return (!queue.isEmpty());
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
