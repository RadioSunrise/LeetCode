// site: https://leetcode-cn.com/problems/permutation-sequence/
// 返回指定的第k个排列

package leetcode;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;





// 第一版用递归的方法，用ArrayList来存放可变的数组，删除获取比较复杂，k的判断也容易出错
package leetcode;

import java.util.ArrayList;

/**
 * 返回指定位置的排列
 */
public class GetPermutation {
    public String getPermutation(int n, int k) {
        int[] factor = factorial(n);
        StringBuilder res = new StringBuilder();
        // 特殊判断 k = n!
        if(k == factor[n]){
            for(int i = n; i >= 1; i--){
                res.append(i);
            }
            return res.toString();
        }
        ArrayList<Integer> arr = new ArrayList<>();
        // ArrayList存放可变数组
        for(int i = 1; i <= n; i++){
            arr.add(i);
        }
        recur(arr, n, k, res, factor);
        return res.toString();
    }
    /**
     * 递归函数
     * @param nums 剩余数组
     * @param n 剩余的数量
     * @param k 第几个排列
     * @param res 存放结果的StringBuilder
     * @param factor 存放阶乘的数组
     */
    public void recur(ArrayList<Integer> nums, int n, int k, StringBuilder res, int[] factor){
        // 结束条件 剩余1个
        if(n == 1){
            res.append(nums.get(0));
            return;
        }
        // 计算首位选第几个数字
        int index = (k - 1) / factor[n - 1];
        // remove方法会返回被删除的数，不用get + remove
        res.append(nums.remove(index));
        // 剩余数组中的第几个排列
        // 注意nextK的计算，这样写的话K不会等于0，从1开始，跟原问题才一样
        int nextK = (k - 1) % factor[n - 1] + 1;
        /*
        // nextK == 0，剩余的反序加进去
        if(nextK == 0){
            for(int i = nums.size() - 1; i >= 0; i--){
                res.append(nums.get(i));
            }
            return;
        }
         */
        // 递归
        recur(nums, n - 1, nextK, res, factor);
    }
    /**
     * 返回阶乘数组, n = [0:9]
     */
    public int[] factorial(int n){
        int[] factor = new int[n + 1];
        factor[0] = 1;
        for(int i = 1; i < n + 1; i++){
            factor[i] = i * factor[i - 1];
        }
        return factor;
    }
    public static void main(String[] args){
        int n = 4;
        int k = 10;
        String res = new GetPermutation().getPermutation(n, k);
        System.out.println(res);
    }
}
