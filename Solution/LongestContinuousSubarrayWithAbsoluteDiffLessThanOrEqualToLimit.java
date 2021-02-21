// site: https://leetcode-cn.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/

public class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {       
    /** 
    * 双指针 + 单调队列实现
    * 双端队列用Deque接口
    */
    public int longestSubarrayDeque(int[] nums, int limit) {
        // 双指针实现
        // 枚举右端点，找到符合的最远的左端点
        int n = nums.length;
        int left = 0;
        int right = 0;
        // 要用合适的数据结构来维护窗口中的最小和最大值

        // 用两个单调队列，一个维护最小一个维护最大
        // 单调递增的最小队列queMin，单调递减的最大队列qurMax
        // 队首分别是最小值和最大值，用双端队列 Deque，Queue 只能单边操作
        Deque<Integer> queMin = new LinkedList<>();
        Deque<Integer> queMax = new LinkedList<>();
        int res = 0;
        while(right < n){
            // right 负责观察
            // 更新max和min
            // 利用双端队列的末尾去除offerLast操作和offerLast末尾添加操作
            while(!queMin.isEmpty() && queMin.peekLast() > nums[right]){
                queMin.pollLast();
            }
            queMin.offerLast(nums[right]);
            while(!queMax.isEmpty() && queMax.peekLast() < nums[right]){
                queMax.pollLast();
            }
            queMax.offerLast(nums[right]);
            // 循环找到最远的左端点
            while(!queMin.isEmpty() && !queMax.isEmpty() && queMax.peekFirst() - queMin.peekFirst() > limit){
                // left要离开区间，判断是否需要维护两个队列
                if(nums[left] == queMax.peekFirst()){
                    queMax.pollFirst();
                }
                if(nums[left] == queMin.peekFirst()){
                    queMin.pollFirst();
                }
                left++;
            }
            // 挑战最大值
            res = Math.max(res, right - left + 1);
            // right右移下一个
            right++;
        }
        return res;
    }
}

class Solution {       
    /** 
    * 双指针 + TreeMap实现
    */
    public int longestSubarrayTreeMap实现(int[] nums, int limit) {
        // 双指针实现
        // 枚举右端点，找到符合的最远的左端点
        int n = nums.length;
        int left = 0;
        int right = 0;
        // 要用合适的数据结构来维护窗口中的最小和最大值

        // TreeMap也是有序的，firstKey()最小，lastKey()最大
        // key是数值，value是这个数值在窗口中的数量
        TreeMap<Integer, Integer> map = new TreeMap<>();

        int res = 0;
        while(right < n){
            // right 负责观察
            // 放进TreeMap里面，更新max和min
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            // 循环找最远的left指针
            while(map.lastKey() - map.firstKey() > limit){
                // nums[left]要离开窗口了
                int count = map.get(nums[left]);
                map.put(nums[left], count - 1);
                // 删掉一个之后，窗口里面已经没有这个值了，把这个key删掉
                if(count == 1){
                    map.remove(nums[left]);
                }
                left++;
            }
            // 挑战最大值
            res = Math.max(res, right - left + 1);
            // right右移下一个
            right++;
        }
        return res;
    }
}
