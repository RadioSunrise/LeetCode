// site: https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof
// 一般做法，因为题目返回的是int，所以不用考虑溢出的问题，但是当问题规模大的时候，还是要考虑的
class Solution_normal {
    public int[] printNumbers(int n) {
        int max = (int)(Math.pow(10,n))-1;
        int[] res = new int[max];
        for(int i = 1; i <= max; i++)
        {
            res[i - 1] = i;
        }
        return res;
    }
}

//用字符串来表示一个数字
