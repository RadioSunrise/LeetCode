// site: https://leetcode-cn.com/problems/rotate-array/

// 旋转数组，要求空间O(1)

// 参考: https://leetcode-cn.com/problems/rotate-array/solution/xuan-zhuan-shu-zu-by-leetcode-solution-nipk/

// 1.循环交换实现
class Solution {
    public void rotate(int[] nums, int k) {
        // 环装交换
        int n = nums.length;
        if(k <= 0){
            return;
        }
        // 求模
        k = k % n;
        // 循环的圈数是n和k的最大公约数，详细推导见官解
        int count = gcd(k, n);
        for(int start = 0; start < count; start++){
            int current = start;
            int prev = nums[current];
            // 直到回到原起点为止，一直向后交换
            int flag = 0;
            while(flag != 1){
                int next = (current + k) % n;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                if(current == start){
                    flag = 1;
                }
            }
        }
    }
    /**
    * 最大公约数
    */ 
    public int gcd(int a, int b){
        return b > 0 ? gcd(b, a % b) : a;
    }
}

// 2.翻转实现
// 先将数组整个翻转，然后翻转前k个，再返回后面的(n - k)个，可以得到结果
// 时间O(2n) = O(n)
class Solution {
    public void rotate(int[] nums, int k) {
        // 环装交换
        int n = nums.length;
        if(k <= 0){
            return;
        }
        // 求模
        k = k % n;
        // 整段翻转
        reverse(nums, 0, n - 1);
        // 翻转前k个
        reverse(nums, 0, k - 1);
        // 翻转剩余部分
        reverse(nums, k, n - 1);
    }
    /**
    * 从start到end翻转数组
    */ 
    public void reverse(int[] nums, int start, int end){
        while(start <= end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
