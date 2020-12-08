package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * site: https://leetcode-cn.com/problems/split-array-into-fibonacci-sequence/
 * 将一个数字字符串划分成斐波那契序列，用回溯法实现
 */
public class SplitArrayFibonacci {
    public List<Integer> splitIntoFibonacci(String S) {
        // 回溯法实现
        List<Integer> list = new ArrayList<>();
        int len = S.length();
        backtrack(list, S, 0, len,0, 0);
        return list;
    }

    /**
     * 用回溯法实现，返回值boolean表示是否还能继续拆分，遇到false表示不能拆分
     * @param list 结果列表
     * @param S 原数字字符串
     * @param index 搜索下标
     * @param length 原字符串S的长度
     * @param expect 当前期望划分的值
     * @param prev 上一次划分的数值
     */
    public boolean backtrack(List<Integer> list, String S, int index, int length, int expect, int prev){
        // 结束条件1，划分到末尾了
        if(index >= length){
            return (list.size() >= 3);
        }
        // 用long来定义，防止溢出
        long curr = 0;
        for(int i = index; i < length; i++){
            // 剪枝1，如果划分的数字长度大于1，且开头为0，则不满足条件
            if(i > index && S.charAt(index) == '0'){
                break;
            }
            // 计算数字
            curr = curr * 10 + (S.charAt(i) - '0');
            // 剪枝2，如果当前数字超过了最大整数，也不满足
            if(curr > Integer.MAX_VALUE){
                break;
            }
            int currInt = (int)curr;
            // list里面有两个或以上的数
            if(list.size() >= 2){
                // 比期望值小，检索下一个位置继续尝试
                if(currInt < expect){
                    continue;
                }
                // 比期望值大，说明再增加位置也不可能，返回false
                else if(currInt > expect){
                    break;
                }
            }
            // 走到这个位置说明满足期望值except，加入结果集
            list.add(currInt);
            // 继续递归，从下一个位置开始
            if(backtrack(list, S, i + 1, length, currInt + prev, currInt)){
                return true;
            }
            // 回溯，删掉本次递归加入的数字，回溯到上层
            else {
                list.remove(list.size() - 1);
            }
        }
        return false;
    }
    public static void main(String[] args){
        String S = "112358130";
        List<Integer> res = new SplitArrayFibonacci().splitIntoFibonacci(S);
        System.out.println(res.toString());
    }
}
