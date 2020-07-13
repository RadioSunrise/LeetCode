// site: https://leetcode-cn.com/problems/move-zeroes/

// 双指针法
// 一个是cur指针(代码里的i)，另一是lastNonZeroFindAt（慢指针）
// 慢指针用于记录最后一个找到的非0元素放在哪里，并不会造成数据的丢失
// 当我们不断发现新的非 0 元素时，我们只是在 “lastnonzerofoundat+1” 第个索引处覆盖它们。
// 此覆盖不会导致任何数据丢失，因为我们已经处理了其中的内容（如果它是非 0 的，则它现在已经写入了相应的索引，或者如果它是 0，则稍后将进行处理）。

class Solution {
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        if(n == 0 || n == 1){
            return;
        }
        int lastNonZeroFindAt = 0;
        // 先遍历一遍非0的元素，放到lastNonZeroFindAt的位置
        // 不会造成数据的丢失
        for(int i = 0; i < n; i++){
            if (nums[i] != 0){
                nums[lastNonZeroFindAt] = nums[i];
                lastNonZeroFindAt++;
            }
        }

        // 非0元素都按顺序放好了，剩余用0进行填充
        for(int i = lastNonZeroFindAt; i < n; i++){
            nums[i] = 0;
        }
    }
}


// 快排思想的一遍版本
// 0当做key值，不等于0放左边
// j 指示下一个交换的位置
class Solution {
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        if(n == 0 || n == 1){
            return;
        }
        // 一遍扫描的做法，利用快排的思想，用0当做key值
        int j = 0;
        for(int i = 0; i < n; i++){
            // 不等于0，交换到左边
            if(nums[i] != 0){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                // j向前移动，指示下一个交换的位置
                j++;
            }
        }
    }
}

// 自己的写法，比较麻烦，双指针一个找0，一个找,0，基于交换的想法来做的
// 很慢
class Solution {
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        if(n == 0 || n == 1){
            return;
        }
        // i找0的位置，j找下一个未交换的非0的位置
        int i = 0, j = 0;
        while (i < n && j < n){
            // i找到一个0
            while (nums[i] != 0){
                i++;
                if (i >= n){
                    return;
                }
            }
            j = i;
            // j找到一个非0
            while (nums[j] == 0){
                j++;
                if (j >= n){
                    return;
                }
            }
            // 交换
            nums[i] = nums[j];
            nums[j] = 0;
        }
    }
}
