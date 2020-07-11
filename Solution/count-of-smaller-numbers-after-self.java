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

// 待增加归并思想的解法
