// site: https://leetcode-cn.com/problems/lemonade-change/submissions/

// 柃檬水找零，分类处理 + 贪心策略，主要变量的定义可以尽可能地少，用两个变量five和ten，不要用数组增加耗时

class Solution {
    public boolean lemonadeChange(int[] bills) {
        // 两种币值的数量：five 和 ten
        int five = 0;
        int ten = 0;
        for(int bill : bills){
            // 根据收到的钱来处理
            // 5块直接收取
            if(bill == 5){
                five++;
            }
            // 10块找回1张5块
            else if(bill == 10){
                if(five <= 0){
                    return false;
                }
                else {
                    // 找一张5块
                    five--;
                }
                ten++;
            }
            else if(bill == 20){
                // 1张10元和1张5元
                if(five >= 1 && ten >= 1){
                    five--;
                    ten--;
                }
                // 3张5元
                else if(five >= 3){
                    five -= 3;
                }
                else {
                    return false;
                }
            }     
        }
        return true;
    }
}
