// site: https://leetcode-cn.com/problems/matrix-cells-in-distance-order/

// 按照到给定具体点的距离（哈密顿距离），对矩阵中的所以点进行排序

class Solution {
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        // 桶排序
        // 最大的距离是最远的行+最远的列
        int maxDistance = Math.max(r0, R - r0 - 1) + Math.max(c0, C - c0 - 1);
        // 桶
        List<List<int[]>> bucket = new ArrayList<>();
        for(int i = 0; i <= maxDistance; i++){
            bucket.add(new ArrayList<>());
        }
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                int dis = distant(i, j, r0, c0);
                bucket.get(dis).add(new int[]{i, j});
            }
        }
        // 从桶加入到结果集中
        int [][] res = new int[R * C][2];
        int index = 0;
        for(int i = 0; i <= maxDistance; i++){
            for(int[] pos : bucket.get(i)){
                res[index++] = pos;
            }
        }
        return res;
    }
    public int distant(int r1, int c1, int r2, int c2){
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
}
