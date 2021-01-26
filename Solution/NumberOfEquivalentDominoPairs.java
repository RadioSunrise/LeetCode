package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/number-of-equivalent-domino-pairs/
 * 找出等价的多米诺骨牌的个数
 */
public class NumberOfEquivalentDominoPairs {
    public int numEquivDominoPairs(int[][] dominoes) {
        // 用数组来模拟哈希表
        // 对于遍历到的多米诺牌，都统一成从小到大的形式，就可以直接比较了
        // 将二个元素的多米诺牌变成1个实数，[x, y] -> 10x + y，其中限定x <= y
        // 因为每个数都是0-9，所以是不会重复的，用一个100的数组即可
        int[] count = new int[100];
        int res = 0;
        for(int[] domino : dominoes){
            int index = 0;
            // 先大后小则交换
            if(domino[0] > domino[1]) {
                index = domino[1] * 10 + domino[0];
            } else {
                index = domino[0] * 10 + domino[1];
            }
            res += count[index];
            count[index]++;
        }
        return res;
    }
    public static void main(String[] args){
        int[][] domines = new int[][]{{1,2},{1,2},{2,1},{3,4},{5,6}};
        int count = new NumberOfEquivalentDominoPairs().numEquivDominoPairs(domines);
        System.out.println(count);
    }
}
