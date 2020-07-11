// site: https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/

// 自己的做法，从右向左扫描，右边的数组全部都排成有序的，然后找当前元素的插入位置，即为count[i]
// 但是这样就很烦，需要多一个arraylist来存放i右边的数，而且插入操作也花时间
// 优化成nums数组上直接操作也行
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        List<Integer> result = new ArrayList<>(n);
        // 存放右边数组
        ArrayList<Integer> back = new ArrayList<>(n);
        for(int i = n-1; i >=0 ; i--){
            // counts[i] = InsertFind(nums[i], back);
            result.add(0,InsertFind(nums[i], back));
        }
        // System.out.println(Arrays.toString(counts));
        return result;
    }
    public static int InsertFind(int a, ArrayList<Integer> list){
        // 将一个数a插入到有序的数组list里面，返回比a小的元素个数
        if(list.size() == 0){
            list.add(a);
            return 0;
        }
        int low = 0;
        int high = list.size() - 1;
        int index = 0;
        int mid = 0;
        while(low <= high){ // 注意条件！！！用low < high或者是low + 1 = high都要做很多麻烦的判断！！！
            mid = low + ((high - low) >> 1);
            if(a <= list.get(mid)){
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        index = low; // 最后插到low的位置
        list.add(index, a);
        return index;
    }
}

// 归并排序 + 索引数组的写法
// 因为原数组会变，所以真正排序的对象变为索引数组，count值还是按索引存回去的
class Solution {
    int[] index; // 索引数组
    int[] helper; // 归并用的辅助数组
    int[] count; // 计数，保存每个元素右边比自己小的个数
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        List<Integer> res = new ArrayList<>(n);
        if (n == 0) {
            return res;
        }
        index = new int[n];
        helper = new int[n];
        count = new int[n];

        for(int i = 0; i < n; i++){
            index[i] = i;
        }
        MergeCountSmall(nums, 0, n-1);
        for(int i = 0; i < n; i++){
            res.add(count[i]);
        }
        return res;
    }

    /**
     * 归并并且计算具体每个元素的逆序数，用实际归并的是索引数组而不是元数组
     * @param nums
     * @param head
     * @param rear
     */
    public void MergeCountSmall(int[] nums, int head, int rear){
        if(head == rear){
            return;
        }
        int mid = head + ((rear - head) >> 1);
        MergeCountSmall(nums, head, mid);
        MergeCountSmall(nums, mid + 1, rear);
        if(nums[index[mid]] <= nums[index[mid + 1]]){
            return;
        }
        MergeTwoAndCountSmall(nums, head, mid, rear);
    }
    public void MergeTwoAndCountSmall(int[] nums, int left_head, int mid, int right_rear){
        // 索引数组，在原本的归并排序中, 最后排完更新的是arr，而这里更新的是index，开始用helper把现在的index存起来
        for (int i = left_head; i <= right_rear; i++) {
            helper[i] = index[i]; // 保存当前的索引
        }

        int i = left_head;
        int right_head = mid + 1;
        int j = right_head;
        int k = left_head;

        for(; k <= right_rear; k++){
            // 左边用完了
            if(i > mid){
                index[k] = helper[j];
                j++;
            }
            // 右边用完了
            else if(j > right_rear) {
                index[k] = helper[i];
                i++;
                count[index[k]] += (right_rear - mid);
            }
            // 左边出
            else if(nums[helper[i]] <= nums[helper[j]]){
                index[k] = helper[i];
                i++;
                count[index[k]] += (j - mid - 1);
            }
            // 右边出
            else {
                index[k] = helper[j];
                j++;
            }
        }
    }
}
