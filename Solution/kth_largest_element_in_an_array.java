// site: https://leetcode-cn.com/problems/kth-largest-element-in-an-array/

class Solution {
    public int findKthLargest(int[] nums, int k) 
    {
        return findKthLargest_Rec(nums, k, 0, nums.length - 1);
    }
    public int findKthLargest_Rec(int[] nums, int k, int head, int rear)
    {
        if(rear > head)
        {
            // 设置左右位置和枢值
            int i = head, j = rear; 
            Random rand = new Random();
            int key_pos = head + rand.nextInt(rear - head + 1); //随机选择枢轴
            swap(nums, key_pos, head); // 与head交换，将交换后序列的head作为枢轴
            int key = nums[head];
            while(j > i)
            {
                while(j > i && nums[j] <= key)
                {
                    j --;
                }
                if(j > i)
                {
                    nums[i] = nums[j];
                }
                while (j > i && nums[i] >= key)
                {
                    i++;
                }
                if(j > i)
                {
                    nums[j] = nums[i];
                }
            }
            nums[i] = key;
            if(i + 1 == k)
            {
                return nums[i];
            }
            else if(i + 1 > k)
            {
                return findKthLargest_Rec(nums, k, head, i - 1);
            }
            else
            {
                return findKthLargest_Rec(nums, k, i + 1, rear);
            }
        }
        return nums[head];
    }
    public void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

// 待添加堆排序版本
