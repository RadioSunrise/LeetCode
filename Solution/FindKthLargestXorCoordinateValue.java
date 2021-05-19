// site: https://leetcode-cn.com/problems/find-kth-largest-xor-coordinate-value/

// 二维异或前缀 + Top K 问题

class FindKthLargestXorCoordinateValue {
    public int kthLargestValue(int[][] matrix, int k) {
        // 二维异或前缀 + Top K 问题
        // 根据异或的性质，令 sum[i][j] 表示以下标 (i, j) 右下角的异或总和，则有
        // sum[i][j] = sum[i - 1][j] XOR sum[i][j - 1] XOR sum[i - 1][j - 1] XOR matrix[i][j]
        // 为了减少下标为 0 的判断，使用 (m + 1) * (n + 1) 大小的 sum
        // 则 sum[i][j] 对应的位置是 matrix[i - 1][j - 1] (1 <= i, j)

        // Top K 问题用优先队列实现

        int m = matrix.length;
        int n = matrix[0].length;
        int[][] sum = new int[m + 1][n + 1];
        // 第一个参数是初始大小，第二参数是 Comparator 
        PriorityQueue<Integer> heap = new PriorityQueue<>(k, (a, b) -> (a - b));
        // 遍历计算
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i - 1][j] ^ sum[i][j - 1] ^ sum[i - 1][j - 1] ^ matrix[i - 1][j - 1];
                // 判断是否放进堆中
                if (heap.size() < k) {
                    heap.add(sum[i][j]);
                } else if (heap.size() >= k && sum[i][j] > heap.peek()) {
                    // 堆的大小到k了，且放入的元素大于堆顶元素，则替换堆顶
                    heap.poll();
                    heap.add(sum[i][j]);
                }
            }
        }
        // 堆顶元素即为答案
        return heap.peek();
    }
}
