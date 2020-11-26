// site: https://leetcode-cn.com/problems/maximum-gap/
// reference: https://leetcode-cn.com/problems/maximum-gap/solution/python3-tong-pai-xu-by-yanghk/

// O(n)时间内计算排序后数值的相邻数字间的最大间距，使用桶排序达到O(n)

class Solution {
    public int maximumGap(int[] nums) {
        int n = nums.length;
        if(n <= 1){
            return 0;
        }
        // 线性时间和空间，桶排序
        // 桶长 bucketSize = ((max - min) / (n - 1))向上取整
        // 桶的数量 bucketNum = ((max - min) / bucket) + 1
        // 先找出最大和最小的数字
        int min = nums[0];
        int max = nums[0];
        for(int num : nums){
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        if(min == max){
            return 0;
        }
        List<List<Integer>> buckets = bucketSort(nums, min, max);
        // 找桶间的最大间距(后一个桶的最小减前一个桶的最大)
        int res = Integer.MIN_VALUE;
        int prevMax = Integer.MIN_VALUE;
        for(List<Integer> bucket : buckets){
            if(bucket.size() != 0 && prevMax != Integer.MIN_VALUE){
                res = Math.max(bucket.get(0)- prevMax, res);
            }
            if(bucket.size() != 0){
                prevMax = bucket.get(bucket.size() - 1);
            }
        }
        return res;
    }
    /**
    * 桶排序
    * @param nums 数组
    * @param min 最小值
    * @param max 最大值
    */
    public List<List<Integer>> bucketSort(int[] nums, int min, int max){
        // 计算桶的长度
        int n =  nums.length;
        int bucketSize = (int)Math.ceil((double)(max - min) / (n - 1));
        // 桶的个数最后要+1，确保最大的数也可以放进去
        int bucketNum = (int)Math.ceil((double)(max - min) / (bucketSize)) + 1;
        // 创建桶
        List<List<Integer>> buckets = new ArrayList<>();
        for(int i = 0; i < bucketNum; i++){
            buckets.add(new ArrayList<>());
        }
        // 计算下标，把每个元素存入桶中
        for(int num : nums){
            buckets.get(getIndex(num, min, bucketSize)).add(num);
        }
        // 桶内排序，方便计算桶之间的最大间距
        for(List<Integer> bucket : buckets){
            Collections.sort(bucket);
        }
        return buckets;
    }
    /**
    * 计算每个数所在的桶的index
    * @param num 数值
    * @param min 最小值
    * @param bucketSize 桶的大小
    */
    public int getIndex(int num, int min, int bucketSize){
        return (num - min) / bucketSize;
    }
}
