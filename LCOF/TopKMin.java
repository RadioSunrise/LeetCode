package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 求一个数组中的最小k个数
  */
public class TopKMin {

    /**
     * 快排partition的做法
     * @param arr
     * @param k
     * @return
     */
    public int[] topKMinPartition(int[] arr, int k){
        if(k == 0 || arr.length == 0){
            return new int[0];
        }
        // 快排搜索
        // 最后一个参数要用k-1，因为partition返回的是下标
        return quickSearch(arr, 0, arr.length - 1, k - 1);
    }
    /**
     * 快排搜索的递归函数
     * @param arr
     * @param left
     * @param right
     * @param k
     * @return
     */
    public int[] quickSearch(int[] arr, int left, int right, int k){
        // partition找分割点
        int index = partition(arr, left, right);
        // 分割点刚好就在k，返回前k个就可以了
        if(index == k){
            // 要index + 1，因为index是下标，是k-1的位置
            return Arrays.copyOf(arr, index + 1);
        }
        // 根据index和k的关系递归
        if(index < k){
            // index 小，在右半边找
            return quickSearch(arr, index + 1, right, k);
        }
        else {
            return quickSearch(arr, left, index - 1, k);
        }
    }
    /**
     * 快排的partition函数，找到当前[left, right]的分割点
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public int partition(int[] arr, int left, int right){
        int pivot = arr[left];
        int i = left;
        int j = right;
        while(i < j){
            while (i < j && arr[j] >= pivot){
                j--;
            }
            arr[i] = arr[j];
            while (i < j && arr[i] <= pivot){
                i++;
            }
            arr[j] = arr[i];
        }
        arr[i] = pivot;
        return i;
    }

    /**
     * 用最大堆实现
     * 最大堆只需要存放k个元素，调整堆logK，最小堆的话要把全部数都放进堆里面，调整推要logN
     * @param arr 数组
     * @param k  数量
     */
    public int[] topKMinHeap(int[] arr, int k){
        if(k == 0 || arr.length == 0){
            return new int[0];
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // 最大堆反过来
                return o2 - o1;
            }
        });
        int size = 0;
        for(int num : arr){
            if(maxHeap.size() < k){
                maxHeap.offer(num);
            }
            // 和堆顶比较大小，比堆顶小就加入
            else if(num < maxHeap.peek()){
                maxHeap.poll();
                maxHeap.offer(num);
            }
        }
        // 放入结果
        int[] res = new int[k];
        for(int i = 0; i < k; i++){
            res[i] = maxHeap.poll();
        }
        return res;
    }

    /**
     * 直接排序
     * @param arr
     * @param k
     */
    public int[] topKMinSort(int[] arr, int k){
        Arrays.sort(arr);
        return Arrays.copyOf(arr, k);
    }

    /**
     * 数字范围有限，计数排序
     * @param arr
     * @param k
     */
    public int[] topKMinCountSort(int[] arr, int k){
        if(k == 0 || arr.length == 0){
            return new int[0];
        }
        if(k == arr.length){
            return arr;
        }
        // 数据的范围是0到10000
        // 统计每个数字的出现次数
        // 0 到 10000 共 10001个数字
        int[] count = new int[10001];
        for(int num : arr){
            count[num]++;
        }
        int[] res = new int[k];
        // 从0开始把前k个数放入结果集
        int i = 0;
        for(int num = 0; num < count.length; num++){
            // 按照出现次数逐个添加
            while (count[num] > 0 && i < k){
                // count计数记得减1
                count[num]--;
                res[i] = num;
                i++;
            }
            // 加够k个了，退出
            if(i == k){
                break;
            }
        }
        return res;
    }

    public static void main(String[] args){
        TopKMin solution = new TopKMin();
        int[] nums = {3,1,2,5,6,9,5,0,8};
        int k = 9;
        int[] res = solution.topKMinCountSort(nums, k);
        System.out.println(Arrays.toString(res));
    }
}
