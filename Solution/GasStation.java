package leetcode;

/**
 * https://leetcode-cn.com/problems/gas-station/
 * 环路上有N个加油站，可以加油也会耗油，如果能跑完一圈则返回起点下标（结果唯一），不能排完则返回-1
 * 参考：https://leetcode-cn.com/problems/gas-station/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--30/
 */
public class GasStation {
    /**
     * 暴力法有很多的重复
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost){
        int n = gas.length;
        // 暴力，每个点作为起点试一试，发现不行就下一个点
        for(int start = 0; start < n; start++){
            // i为起点并加油，余量初始值为gas[i]
            int pos = start;
            int remain = gas[start];
            // 还能到下一个点就继续循环
            while(remain >= cost[pos]){
                // 余量 = 余量 - 从这个点出发到下一个点的消耗 + 下一个点的补充（环路取余）
                remain = remain - cost[pos] + gas[(pos + 1) % n];
                pos = (pos + 1) % n;
                // 能回到原点
                if(pos == start){
                    return start;
                }
            }
        }
        return -1;
    }

    /**
     * 改进的方法
     * 暴力法中存在很多重复，pos会运行到start之前走过的位置
     * 因此考虑某一个点出发最远能到哪个点，比如从i出发最多只能到j，那个i到j之间的点肯定都是不能满足出发走一圈的
     * 证明：假设i+1可以走一圈，那么i+1可以走到j+1，而从i可以走到i+1，则从i可以走到j+1，矛盾
     * 因此i到j之间的点都不不满足的，因此发现i不满足条件（只能到j不能一圈）的时候，下一轮直接考虑j+1
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit2(int[] gas, int[] cost){
        int n = gas.length;
        for(int i = 0; i < n; i++){
            int j = i;
            int remain = gas[j];
            while (remain >= cost[j]){
                // 减去花费和新的补给
                remain = remain - cost[j] + gas[(j + 1) % n];
                j = (j + 1) % n;
                // 可以一圈则返回
                if(j == i){
                    return i;
                }
            }
            // j < i则说明走不够一圈，只能绕到已经遍历过的i，所以必定不存在解
            if(j < i){
                return -1;
            }
            // 下一轮直接考虑j+1，因为for循环i会+1，所以i=j，下一轮+1
            i = j;
        }
        return -1;
    }

    public static void main(String[] args){
        int[] gas = new int[]{1,2,3,4,5};
        int[] cost = new int[]{3,4,5,1,2};
        int point = new GasStation().canCompleteCircuit2(gas, cost);
        System.out.println(point);
    }
}
