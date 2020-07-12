// site: https://leetcode-cn.com/problems/dungeon-game/

// 这道题的dp从左上角到右下角是不行的，如果从左上角到右下角的话，除了需要保存当前的最少初始hp，还需要保存当前路径和
// 其中初始hp越小越好，而路径和则是越大越好，而且随着路径的不同这些值也会变，不符合动态规划的无后效性
// 所以从右下角开始向左上角递归，因为右下角的状态是确认的，所以从右下反推左上会比较好

// dp解法1
// dp定义：在这一格需要的最少hp，根据当前位置的加减情况和下一个方向的结果来考虑
// 例如在当前(i,j)位置，需要做的操作是-10，走到右边需要操作后剩余5点(dp[i][j+1]=5)，走到下面需要操作后剩余1点(dp[i+1][j]=1)，那么选下方就可以了
// 因为选下方的话在(i,j)位置需要11，而走右边在(i,j)位置需要15
// dp[i][j] = max(1, min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j])
// base case: 右下角的hp需要max(1, 1-dungeon[m][n])
// 终点需要的最少血量，如果终点扣hp，则为1+abs(dungeon[i][j])
// 加hp的话直接1就可以了(1 > 1 - N+)
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        // dp[i][j] 表示的是在(i,j)需要的最小生命值
        // 共m*n的大小
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m][n];
        for (int i = m - 1; i >= 0; i--){
            for (int j = n - 1; j >= 0; j--){
                if (i == m-1 && j == n-1){ // 右下角的情况
                    // 终点需要的最少血量，如果终点扣hp，则为1+abs(dungeon[i][j])
                    // 加hp的话直接1就可以了
                    dp[i][j] = Math.max(1, 1 - dungeon[i][j]);
                }
                else if (i == m -1){ // 最后1行
                    dp[i][j] = Math.max(1, dp[i][j + 1] -dungeon[i][j]); 
                }
                else if (j == n - 1){ // 最后1列
                    dp[i][j] = Math.max(1, dp[i + 1][j] - dungeon[i][j]);
                }
                else{
                    dp[i][j] = Math.max(1, Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j]);
                }
            }
        }
        return dp[0][0];
    }
}




// dp解法2
// dp定义：dp[i][j]表示(i,j)向往右或下走，最少需要多少hp
// 最优解对于的dp[m][n]是1，因为希望到最后1格剩余血量最少，为1不会死就可以了
// 最后1格需要扣5点，剩余1，所以之前一步结算完要剩余6点，这样最后1格减5之后也会剩下1点
// 其他位置也是一样的，考虑向右和向下，往哪个方向需要的hp最少，就往哪个方向走
// 这样的dp只需要考虑右或下方向的dungeon值和dp值的差
// right = max(dp[i][j+1] - dungeon[i][j+1], 1)，因为下一个可能是加hp，例如+30点之后剩余6点hp，那么我需要-24就可以了，但初始hp要>1才行
// down = max(dp[i+1][j] - dungeon[i+1][j], 1)
// dp[i][j] = min(right, down)
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        // dp[i][j] 表示的是(i,j)向往右或下走，最少需要多少hp
        // 共m*n的大小
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m][n];
        // 从右下往左上遍历
        // base case
        dp[m-1][n-1] = 1; // 最后一格剩余1HP对应最优解
        // 最后一行，只能向右
        for(int j = n-2; j >= 0; j--){
            dp[m - 1][j] = Math.max(dp[m - 1][j + 1] - dungeon[m - 1][j + 1], 1);
        }
        // 最后一列，只能向下
        for(int i = m - 2; i >= 0; i--){
            dp[i][n-1] = Math.max(dp[i + 1][n - 1] - dungeon[i + 1][n - 1], 1);
        }
        for(int i = m - 2; i >= 0; i--){
            for(int j = n - 2; j >= 0; j--){
                // 向右需要的最低hp
                int right = Math.max(dp[i][j + 1] - dungeon[i][j+1], 1);
                // 向下
                int down = Math.max(dp[i + 1][j] - dungeon[i + 1][j], 1);
                // 选最小的
                dp[i][j] = Math.min(right, down);
            }
        }
        return Math.max(dp[0][0] - dungeon[0][0], 1);
    }
}

// 解法2快一点的版本
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        // dp[i][j] 表示的是(i,j)向往右或下走，最少需要多少hp
        // 共m*n的大小
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m][n];
        // 从右下往左上遍历
        for(int i = m - 1; i >= 0; i--){
            for(int j = n - 1; j >= 0; j--){
                if(i == m-1 && j == n-1){ // 右下角的情况
                    dp[i][j] = 1;
                }
                else if(i == m - 1){ //最后一行
                    dp[i][j] = Math.max(dp[i][j+1] - dungeon[i][j+1], 1);
                }
                else if(j == n-1){ // 最后一列
                    dp[i][j] = Math.max(dp[i+1][j] - dungeon[i+1][j], 1);
                }
                else{
                    // 向右需要的最低hp
                    int right = Math.max(dp[i][j + 1] - dungeon[i][j+1], 1);
                    // 向下
                    int down = Math.max(dp[i + 1][j] - dungeon[i + 1][j], 1);
                    // 选最小的
                    dp[i][j] = Math.min(right, down);
                }
            }
        }
        return Math.max(dp[0][0] - dungeon[0][0], 1);
    }
}
