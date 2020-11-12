// site: https://leetcode-cn.com/problems/sort-array-by-parity-ii/submissions/

// 将原数组A的奇数和偶数元素分别放到奇数位和偶数位

// O(n)的做法
class Solution {
    public int[] sortArrayByParityII(int[] A) {
        int n = A.length;
        int[] ans = new int[n];
        int i = 0;
        int j = 1;
        for(int num : A){
            if(num % 2 == 0){
                ans[i] = num;
                i += 2;
            }
            else {
                ans[j] = num;
                j += 2;
            }
        }
        return ans;
    }
}
