// site: https://leetcode-cn.com/problems/container-with-most-water/

// 和Two Sum II一样，使用双指针的移动来缩减搜索的空间

class Solution {
    public int maxArea(int[] height) {
        // 双指针缩减搜索空间
        int len = height.length;
        int left = 0;
        int right = len - 1;
        int maxVal = 0;
        while(left < right) {
            int capacity = (right - left) * Math.min(height[left], height[right]);
            if (maxVal < capacity) {
                maxVal = capacity;
            }
            // 当其中一边的高度比较低，高度低的指针移动，排除这个位置和其他位置的组合
            // 如height[left] < height[right]
            // 当right向left靠近，高度不会增加，宽度则会减少，因此装水的容量必定减少
            // 因此left和其他柱子（位于left和right之间）的组合都必定会比[left,right]的组合小
            // 所以可以排除这一系列的left，则left++
            if(height[left] < height[right]){
                left++;
            }
            // 同理，如果height[right] < height[left]
            // 则left向right靠近必定会使容量减少
            // 这一系列的right组合都可以排除
            else{
                right--;
            }
        }
        return maxVal;
    }
}
