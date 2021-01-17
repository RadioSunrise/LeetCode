// site: https://leetcode-cn.com/problems/check-if-it-is-a-straight-line/

// 判断一些点是否在同一直线上

class CheckIfItIsAStraightLine {
    public boolean checkStraightLine(int[][] coordinates) {
        // 因为至少有两个点，所以固定第一个和第二点，计算斜率k
        // 遍历剩余点，和第一个点计算斜率，如果出现与k不同则false
        // 为了防止除0出错，将等号两边的分式变成乘的形式
        
        // (y1-y0) / (x1-x0) = (yi-y0) / (xi-x0) 变为
        // (y1-y0) * (xi-x0) = (yi-y0) * (x1-x0)

        for(int i = 2; i < coordinates.length; i++){
            if((coordinates[1][1] - coordinates[0][1]) * (coordinates[i][0] - coordinates[0][0]) != (coordinates[i][1] - coordinates[0][1]) * (coordinates[1][0] - coordinates[0][0])){
                return false;
            }
        }
        return true;
    }
}
