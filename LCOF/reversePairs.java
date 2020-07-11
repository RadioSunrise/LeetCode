// site: https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/

// 要求出逆序对，可以用归并排序过程中的合并操作
// 合并操作实际上消除逆序对的过程
// 当一个数字的左半边L和右半边R已经有序的时候
// 例如下面这个例子
// L = [8, 12, 16, 22, 100]   R = [9, 26, 55, 64, 91]  M = [8]
        |                          |
       lPtr                       rPtr
// 当rPtr右移，发现9比12小的时候，说明9这个数字应该排在12的左边，而现在9在12的右边
// 同样的，对于所有比12大的数，9都应该在他们的左边，所以当右指针rPtr移动的时候
// 逆序数count应该加上从i到left_read，即left_lear - i + 1个逆序对
// L = [8, 12, 16, 22, 100]   R = [9, 26, 55, 64, 91]  M = [8, 9]
           |                          |
          lPtr                       rPtr
          
// 归并思想的解法
// 如果两边排序完之后，发现是不需要合并的，即nums[mid]<=nums[mid+1]，则可以提早退出
// 不一定是右边出队时候统计，左边出队的时候统计也是可以的，但是要考虑右边数组用完的情况
// 如果右边数组用完了，那么左边出队就要把右边的加上

// 总结：1.左有序数组每一个元素出队 -> 加上右边已经出队过的数量
//       2.右有序数组每一个元素出队 -> 加上左边还没出队的数量
// 两种选一种，两种都考虑就重复计算了

class Solution {
    public int reversePairs(int[] nums) {
        int n = nums.length;
        if(n == 0){
            return 0;
        }
        int[] nums_copy = new int[n];
        for(int i = 0; i < n; i++){
            nums_copy[i] = nums[i];
        }
        int[] temp = new int[n];
        return reversePairsCount(nums_copy, temp, 0, n-1);
    }
    public int reversePairsCount(int[] nums, int[] temp, int head, int rear){
        if(head == rear){
            return 0;
        }
        int mid = head + ((rear - head) >> 1);
        int left_count = reversePairsCount(nums, temp, head, mid);
        int right_count = reversePairsCount(nums, temp, mid + 1, rear);
        if(nums[mid] <= nums[mid + 1]){
            // 如果两边排完之后，整个序列都完全排好序了
            // 提早退出
            return left_count + right_count;
        }
        int merge_count = MergeCount(nums, temp, head, mid + 1, rear);
        // System.out.println(Arrays.toString(nums));
        return left_count + right_count + merge_count;
    }
    public int MergeCount(int[] nums, int[] temp, int l_head, int r_head, int r_rear){
        int i = l_head;
        int l_rear = r_head - 1;
        int j = r_head;
        int k = l_head;
        int count = 0;
        while(i <= l_rear && j <= r_rear){
            if(nums[i] <= nums[j]){ //算逆序数，右边严格大于左边才算
                temp[k++] = nums[i++];
                // count += (j - r_head);
            }
            else{
                temp[k++] = nums[j++];
                // 增加的数量是l_rear-i+1,
                count += (l_rear - i + 1); 
            }
        }
        while(i <= l_rear){
            temp[k++] = nums[i++];
            // 如果是根据前有序数组发生归并时来增加逆序数
            // 当右有序数组用完的时候，左边出队就要加上右边全部的数量
            // 因为当前右边出过的数量 = 右边的全部
            // count += r_rear - r_head + 1;
        }
        while(j <= r_rear){
            temp[k++] = nums[j++];
        }
        for(k = l_head; k <= r_rear; k++){
            nums[k] = temp[k];
        }
        return count;
    }
}
