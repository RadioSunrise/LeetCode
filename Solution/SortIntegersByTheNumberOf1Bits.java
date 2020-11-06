package leetcode;

import java.util.*;

/**
 * site: https://leetcode-cn.com/problems/sort-integers-by-the-number-of-1-bits/
 * 根据二进制中1的数量来排序
  */

public class SortIntegersByTheNumberOf1Bits {
    /**
     * 统计1的个数，再调用Collections.sort来排序
     * @param arr
     * @return
     */
    public int[] sortByBitsByNumOfOne(int[] arr) {
        int n = arr.length;
        // 最大10000，开10001个位置
        int[] countArr = new int[10001];
        List<Integer> list = new ArrayList<>();
        for(int num : arr){
            list.add(num);
            countArr[num] = countOne(num);
        }
        // 调用Arrays.sort来排序
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // 按1的个数来排
                if(countArr[o1] != countArr[o2]){
                    return countArr[o1] - countArr[o2];
                }
                // 1的个数一样就按数值排序
                else {
                    return o1 - o2;
                }
            }
        });
        int[] ans = new int[n];
        for(int i = 0; i < n; i++){
            ans[i] = list.get(i);
        }
        return ans;
    }
    /**
     * 统计x的二进制有多少个1
     */
    public int countOne(int x){
        int count = 0;
        while(x != 0){
            count += (x & 1);
            x = x >> 1;
        }
        return count;
    }

    /**
     * 通过countOne的预处理，递推得到每个0-10000每个数的1的个数
     * 递推式：bit[i] = bit[i >> 1] + (i & 1);
     * @param arr
     */
    public int[] sortByBits(int[] arr) {
        int n = arr.length;
        // 最大10000，开10001个位置
        int[] countArr = new int[10001];
        for(int i = 1; i <= 10000; i++){
            countArr[i] = countArr[i >> 1] + (i & 1);
        }
        List<Integer> list = new ArrayList<>();
        for(int num : arr){
            list.add(num);
        }
        // 调用Arrays.sort来排序
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // 按1的个数来排
                if(countArr[o1] != countArr[o2]){
                    return countArr[o1] - countArr[o2];
                }
                // 1的个数一样就按数值排序
                else {
                    return o1 - o2;
                }
            }
        });
        int[] ans = new int[n];
        for(int i = 0; i < n; i++){
            ans[i] = list.get(i);
        }
        return ans;
    }

    public static void main(String[] args){
        int[] arr = new int[]{0,1,2,4,8,3,5,6,7};
        int[] ans = new SortIntegersByTheNumberOf1Bits().sortByBits(arr);
        System.out.println(Arrays.toString(ans));
    }
}
