// site: https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/

// 因为要找连续的，所以用滑动窗口法
// 代码中的[i,...,j]是常用的左闭右开区间
// j是不包括的，所以j的出界判断是j <= (target / 2) + 2，当窗口的最右值大于(target / 2) + 1的时候，窗口肯定找不到合适的值
class Solution {
    public int[][] findContinuousSequence(int target) {
        int i = 1; // 窗口左边界
        int j = 1; // 窗口右边界
        int sum = 0; // 当前窗口的累加和
        List<int[]> result = new ArrayList<>();
        while(i <= target / 2 && j <= (target / 2) + 2){ //增加j的出界判断
            if(sum < target){
                // sum < taregt，j向右滑动
                sum += j;
                j++;
                // System.out.println("after shift , j is " + j);
            }
            else if(sum > target){
                sum -= i; // i向右移动，sum = sum - i
                i++;
            }
            else if(sum == target){
                int[] arr = new int[j-i];
                for(int k = i; k < j; k++){
                    arr[k-i] = k;
                }
                result.add(arr);
                sum -= i; //i向右移，找下一个起始点
                i++;
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}
