// site: https://leetcode-cn.com/problems/subarrays-with-k-different-integers/

// reference: https://leetcode-cn.com/problems/subarrays-with-k-different-integers/solution/k-ge-bu-tong-zheng-shu-de-zi-shu-zu-by-l-ud34/

public class SubarraysWithKDifferentIntegers {
    public int subarraysWithKDistinct(int[] A, int K) {
        // 如果直接用双指针，左边界固定的时候右边界是不固定的，右边界在一个连续的区间里
        // 恰好 K 个不同的整数构成，可以转化为求 最多 K 个不同的 和 最多 K-1 个不同的 的差值
        // 因为最多 K 个不同，而且是连续子数组，可以用双指针来求解
        // 双指针通常解决“最多”、“最少”等等
        return maxKDistinct(A, K) - maxKDistinct(A, K-1);

    }

    /**
    * 求解最多有 K 个不同的整数构成的子数组的个数
    * @param A array
    * @param K 
    */
    public int maxKDistinct(int[] A, int K){
        int len = A.length;
        // 输入数组的值的范围值在1到A.length
        int[] freq = new int[len + 1];
        // 左右指针（区间是左闭右开的）
        int left = 0;
        int right = 0;
        // 子区间[left, right)里不同整数的个数，左闭右开
        int count = 0;
        int res = 0;
        while(right < len){
            // right是现在看到的
            // 第一次出现则需要加1
            if(freq[A[right]] == 0){
                count++;
            }
            freq[A[right]]++;
            right++;
            // 维护左边界的移动
            // 用while循环，因为可能count - k > 1
            while(count > K){
                freq[A[left]]--;
                // 左边界移出区间之后，频数减到0则需要减少count
                if(freq[A[left]] == 0){
                    count--;
                }
                left++;
            }
            // 退出while循环表示区间内count <= k
            // 所以此时期间的满足最多K个不同整数的，要更新结果
            // 此时子区间的长度 = 最多有 K 个不同的整数构成的子数组的个数（区间是左闭右开的）
            // 如此时区间内[1,2,3], K = 3, 则子区间有[1],[1,2],[1,2,3]，个数等于区间长度
            res += right - left;
        }
        return res;
    }
}
