package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/4sum-ii/
 * 四数之和II，A、B、C、D四个数组，计算有多少个元组(i, j, k, l)，使得A[i] + B[j] + C[k] + D[l] = 0
 * 四个数组的长度均为N
 * 用两个O(n^2)的双重循环来代替O(n^4)的四重循环，中间通过HashMap存储和与对数
 */
public class FourSumII {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int res = 0;
        int N = A.length;
        // key是剩余数值，value是(A[i] + B[j] = key)的个数，保存(i,j)对的数量
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                int key = 0 - A[i] - B[j];
                map.put(key,map.getOrDefault(key, 0) + 1);
            }
        }
        // 处理C、D
        for(int k = 0; k < N; k++){
            for(int l = 0; l < N; l++){
                int residual = C[k] + D[l];
                if(map.containsKey(residual)){
                    res += map.get(residual);
                }
            }
        }
        return res;
    }

    /**
     * 另一种写法
     */
    public int fourSumCount2(int[] A, int[] B, int[] C, int[] D) {
        int res = 0;
        int N = A.length;
        // key是剩余数值，value是(A[i] + B[j] = key)的个数，保存(i,j)对的数量
        Map<Integer, Integer> map = new HashMap<>();
        for(int u : A){
            for (int v : B){
                map.put(u + v, map.getOrDefault(u + v, 0) + 1);
            }
        }
        // 处理C、D
        for(int u : C){
            for(int v : D){
                if(map.containsKey(-u - v)){
                    res += map.get(-u - v);
                }
            }
        }
        return res;
    }

    public static void main(String[] args){
        int[] A = new int[] {1, 2};
        int[] B = new int[] {-2, -1};
        int[] C = new int[] {-1, 2};
        int[] D = new int[] {0, 2};
        int res = new FourSumII().fourSumCount2(A, B, C, D);
        System.out.println(res);
    }
}
