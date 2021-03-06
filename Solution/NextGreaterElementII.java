// site: https://leetcode-cn.com/problems/next-greater-element-ii/

// 单调栈 + 循环数组

public class NextGreaterElementII {
    public int[] nextGreaterElements(int[] nums) {
        // 单调栈 + 循环数组
        // 栈中存放的是下标，从栈底到栈顶的下标在nums中的值是单调不增的
        // 当遍历到位置i时，考虑当前栈顶元素top，如果栈顶元素对应的值nums[top]小于nums[i]，并且将top弹出
        // 则res[top] = nums[i]
        // 由栈的单调性质，只遍历一遍是不够的，需要将数组拉直，并且重复前n-1个元素
        // 否则会有元素找不到答案，所以遍历的长度是2n-1用取模的方式找原数值

        int n = nums.length;
        int[] res = new int[n];
        // 初始化为-1，最大的那个就不用额外操作了
        Arrays.fill(res, -1);
        Deque<Integer> stack = new LinkedList<>();
        for(int i = 0; i < (2 * n - 1); i++){
            while(!stack.isEmpty() && nums[i % n]  > nums[stack.peek()]){
                res[stack.peek()] = nums[i % n];
                stack.pop();
            }
            // 以这个位置的值nums[i]为答案的已经弹出去了，把自己入栈
            stack.push(i % n);
        }
        return res;
    }
}
