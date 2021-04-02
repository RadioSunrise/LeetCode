// site: https://leetcode-cn.com/problems/trapping-rain-water/

// 接雨水

class Solution {
    public int trap(int[] height) {
        // 暴力做法
        // 找到每个下标两边最高的柱
        // 两个边界处是不能储水的

        int n = height.length;
        int res = 0;
        for(int i = 1; i < n - 1; i++){
            int currHeight = height[i];
            
            // 往两边找最高的，两边的最高值要高于当前高度
            // 往左
            int left = Integer.MIN_VALUE;
            for(int j = i - 1; j >= 0; j--){
                left = Math.max(left, height[j]);
            }
            // 小于等于当前高度则说明这一格不能存水
            if(left <= currHeight){
                continue;
            }

            // 往右
            int right = Integer.MIN_VALUE;
            for(int j = i + 1; j < n; j++){
                right = Math.max(right, height[j]);
            }
            // 小于等于当前高度则说明这一格不能存水
            if(right <= currHeight){
                continue;
            }

            // 这一个小标的存储量等于left和right的最小值减去当前高度（宽度是1）
            res += Math.min(left, right) - currHeight;
        }
        return res;
    }
}

// 动态规划
pulic class TrappingRainWater {
    public int trap(int[] height) {
        // 预处理 / 动态规划
        // 找到每个下标两边最高的柱
        // 用两个数组存起来，这样就不需要重复遍历找最高值了

        int n = height.length;
        if(n == 0){
            return 0;
        }

        // 计算两边的最大值
        // 左边，从左往右计算
        // leftMax[i] = [0, i] 区间内最高的高度
        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for(int i = 1; i < n; i++){
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        // 右边，从右往左计算
        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for(int i = n - 2; i >= 0; i--){
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        // 和暴力法一样的这个下标遍历
        // 不用往两边遍历而是直接查数组
        int res = 0;
        for(int i = 1; i < n - 1; i++){
            int curr = height[i];
            if(leftMax[i] <= curr || rightMax[i] <= curr){
                continue;
            } else {
                res += Math.min(leftMax[i], rightMax[i]) - curr;
            }
        }
        return res;
    }
}

// 单调栈
class Solution {
    public int trap(int[] height) {
        // 单调栈
        // 栈存储下标，维护从栈底到栈顶的栈元素对应的高度递减
        // 相当于是保存离每个下标 a 最近的，比height[a]高的两边的下标
        
        int n = height.length;
        if(n == 0){
            return 0;
        }

        Deque<Integer> stack = new LinkedList<>();
        int res = 0;
        for(int i = 0; i < n; i++){
            // 如果当前位置的height[i] 大于 height[peek()]
            // 则需要维护栈的单调性质
            // 意义是遇到高度上升区间，则开始计算储水量
            while(!stack.isEmpty() && height[i] > height[stack.peek()]){
                // 取出 top
                int top = stack.pop();
                if(stack.isEmpty()){
                    break;
                }
                // 取出 top 之后, peek() 就是 left
                // 计算面积
                int left = stack.peek();
                int currLen = i - left - 1;
                int currHeight = Math.min(height[i], height[left]) - height[top];
                res += currLen * currHeight;
            }
            stack.push(i);
        }
        return res;
    }
}

// 双指针
// 没搞懂待学习
