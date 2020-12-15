// site: https://leetcode-cn.com/problems/monotone-increasing-digits/submissions/

// 给定一个非负整数N，找到一个小于等于N且最大的递增数字（每一位都非严格递减）

class Solution {
    /**
    * 贪心方法
    * 从后往前找到第一对逆序的a[i] > a[i + 1]
    * a[i] = a[i] - 1, 从a[i + 1]到a[n - 1] = 9即可
    * 如1234321, 从后往前第一对逆序对是43，a[i] = 4, a[i + 1] = 3
    * a[i] = 4 - 1 = 3, a[i + 1] = ... = a[n - 1] = 9
    * ans = 1233999
    */
    public int monotoneIncreasingDigits(int N) {
        String nums = String.valueOf(N);
        char[] arr = nums.toCharArray();
        int n = arr.length;
        int index = -1;
        // 从后往前找降序对
        for(int i = n - 1; i > 0; i--){
            if(arr[i - 1] > arr[i]){
                index = i;
                // 因为if的条件是arr[i-1] > arr[i]，所以arr[i - 1] > 0
                arr[i - 1]--;
            }
        }
        // 从index开始往后全部=9
        if(index > -1){
            for(int i = index; i < n; i++){
                arr[i] = '9';
            }
        }
        // 09也会变成9返回的，用String处理会比较方便
        return Integer.parseInt(new String(arr));
    }
}
