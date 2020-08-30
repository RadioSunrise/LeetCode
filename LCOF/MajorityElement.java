// site: https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/

// 找出众数

// 摩尔投票法
// 投票过程：众数+1，非众数-1，则最后投票和必定大于0
// 所以在遍历nums的过程中，如果前面a个数的投票和等于0，则后面(n-a)个数的投票和一定大于0
// 所以在正负抵消之后，把当前num当做众数，继续遍历，每次可以缩小区间

// 原理
// 为构建正负抵消，假设数组首个元素 n1为众数，遍历统计票数，当发生正负抵消时，**剩余数组的众数一定不变** ，这是因为（设真正的众数为 x ）;
// 1. 当 n1 = x，抵消的数字中，有一半是x，所以剩余部分中x出现次数仍然大于1/2
// 2. 当 n1 != x，抵消的数字中，x出现的次数必定小于等于一半
// 所以最后能够保证结果正确

class Solution {
    public int majorityElement(int[] nums) {
        // 摩尔投票法
        // 令res = nums[0]
        int res = 0;
        int vote = 0;
        for(int num : nums){
            // vote == 0，将当前num作为众数，继续投票
            // 当前一段子串的投票和等于0，后面一段的众数是不变的，即不影响最终的结果
            if(vote == 0){
                res = num;
            }
            // 投票
            if(num == res){
                vote++;
            }
            else{
                vote--;
            }
        }
        // 因为保证一定存在众数，所以不需要额外的验证步骤
        return res;
    }
}

// Hash统计法
class Solution {
    public int majorityElement(int[] nums) {
        int len = nums.length;
        if(len == 1 || len == 2){
            return nums[0];
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num : nums){
            if(map.containsKey(num)){
                int times = map.get(num);
                if(times + 1 > len / 2){
                    return num;
                }
                map.put(num, times + 1);
            }
            else{
                map.put(num, 1);
            }
        }
        return -1;
    }
}

// 排序法
class Solution {
    public int majorityElement(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        return nums[len / 2];
    }
}
