// site: https://leetcode-cn.com/problems/spiral-matrix/

// 螺旋得从外到里遍历矩阵

// 模拟方向的变化

// 可以用边界来换方向，就不用设置一个O(mn)的visited矩阵了

// 待补充

// 第一版用了O(mn)的空间
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new LinkedList<>();
        // 右下左上四个方向，遇到边界或者访问过的点是时候换方向
        int[][] directions = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int m = matrix.length;
        if(m == 0){
            return result;
        }
        int n = matrix[0].length;
        int total = m * n;
        boolean[][] visited = new boolean[m][n];
        int xIndex = 0;
        int yIndex = 0;
        int dirIndex = 0;
        for(int i = 0; i < total; i++){
            result.add(matrix[xIndex][yIndex]);
            visited[xIndex][yIndex] = true;
            int nextRow = xIndex + directions[dirIndex][0];
            int nextCol = yIndex + directions[dirIndex][1];
            // 到边界了或者遇到了访问过的位置，转向
            if(nextRow < 0 || nextRow >= m || nextCol < 0 || nextCol >= n || visited[nextRow][nextCol]){
                dirIndex = (dirIndex + 1) % 4;
            }
            xIndex += directions[dirIndex][0];
            yIndex += directions[dirIndex][1];
        }
        return result;
    }
}

// 202103015
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        // 顺序访问，走完边界就要转向（可以代替边界的收缩）
        // 增加 visited 记录访问位置
        // 四个方向，右下左上
        int[][] dirs = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        List<Integer> res = new ArrayList<>();
        // 总个数 total
        int total = m * n;
        int row = 0;
        int col = 0;
        // 选方向
        int dirIndex = 0;
        // 0 到 total
        for(int i = 0; i < total; i++){
            res.add(matrix[row][col]);
            visited[row][col] = true;
            // 计算下一个位置，判断是否需要转向
            int nextRow = row + dirs[dirIndex][0];
            int nextCol = col + dirs[dirIndex][1];
            // 越界或者访问判断
            if(nextRow < 0 || nextRow >= m || nextCol < 0 || nextCol >= n || visited[nextRow][nextCol]){
                // 转向
                dirIndex = (dirIndex + 1) % 4;
            }
            // 计算下一个位置
            row += dirs[dirIndex][0];
            col += dirs[dirIndex][1];
        }
        return res;
    }
}
