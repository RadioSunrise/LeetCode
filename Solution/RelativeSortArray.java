package leetcode;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/relative-sort-array/
 * 将arr1的元素按照arr2中的出现顺序排序，arr2中没出现的则按照元素本身的数值排序
 * arr2的元素均不相同，且都出现在arr1中
 * 0 <= arr1[i] <= 1000
 */
public class RelativeSortArray {
    /**
     * 用自定义规则加list的sort排序
     * @param arr1
     * @param arr2
     * @return
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        // 按定义规则排序
        // 先用HashMap把arr2中的元素按照下标存起来
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < arr2.length; i++){
            map.put(arr2[i], i);
        }
        // arr1存在list里面用来排序
        List<Integer> list = new ArrayList<>();
        for(int num : arr1){
            list.add(num);
        }
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // 如果其中一个在arr2中，即在map中，则按照map的value（arr2中位置）来排序
                // 因为arr1[i]的数字不超过1000，所以不在map中的value用1001表示
                if(map.containsKey(o1) || map.containsKey(o2)){
                    return map.getOrDefault(o1, 1001) - map.getOrDefault(o2, 1001);
                }
                // 两个都不再map中，则按照数值排序
                else {
                    return o1 - o2;
                }
            }
        });
        int[] ans = new int[arr1.length];
        int i = 0;
        for(int num : list){
            ans[i++] = num;
        }
        return ans;
    }

    /**
     * 计数排序
     * @param arr1
     * @param arr2
     * @return
     */
    public int[] relativeSortArrayBucket(int[] arr1, int[] arr2) {
        // arr1的数字范围是0到1000，共1001个可能性，用桶来保存
        int[] frequency = new int[1001];
        List<Integer> list = new ArrayList<>();
        // 统计arr1的频率
        for(int num : arr1){
            frequency[num]++;
        }
        // 按照arr2的顺序存入list
        for(int num : arr2){
            while(frequency[num] > 0){
                frequency[num]--;
                list.add(num);
            }
        }
        // arr2遍历完之后，遍历剩余的frequency
        for(int i = 0; i < 1001; i++){
            while (frequency[i] > 0){
                frequency[i]--;
                list.add(i);
            }
        }
        int[] ans = new int[arr1.length];
        int i = 0;
        for(int num : list){
            ans[i++] = num;
        }
        return ans;
    }
    public static void main(String[] args){
        int[] arr1 = new int[]{2,3,1,3,2,4,6,7,9,2,19};
        int[] arr2 = new int[]{2,1,4,3,9,6};
        int[] res = new RelativeSortArray().relativeSortArrayBucket(arr1, arr2);
        System.out.println(Arrays.toString(res));
    }
}
