// site: https://leetcode-cn.com/problems/24-game/

// 给定四个1-9的数字，加减乘除凑24点

// 递归回溯的思想
class Solution {

    static final double ERROR = 1e-6;
    static final int TARGET = 24;
    static final int ADD = 0, MULTIPLY = 1, SUBTRACT = 2, DIVIDE = 3;

    public boolean judgePoint24(int[] nums) {
        // 递归回溯，过程：
        // 4个数选2个，运算完变成1个
        // 然后就是三个数judge24
        List<Double> list = new ArrayList<>();
        for(int num : nums){
            list.add((double) num);
        }
        return check(list);
    }
    /**
    * 递归函数check，根据当前list的元素judge是能构成24
    */
    public boolean check(List<Double> list){
        int len = list.size();
        // 列表为空
        if(len == 0){
            return false;
        }
        // 递归结束条件，剩下一个数
        if(len == 1){
            // 且这个数等于24
            if(Math.abs(list.get(0) - TARGET) < ERROR){
                return true;
            }
            return false;
        }
        // 选两个数
        for(int i = 0; i < len; i++){
            for(int j = 0; j < len; j++){
                if(j != i){
                    double n1 = list.get(i);
                    double n2 = list.get(j);
                    // 保存剩余元素
                    List<Double> listNext = new ArrayList<>();
                    for(int k = 0; k < len; k++){
                        if(k != i && k != j){
                            listNext.add(list.get(k));
                        }
                    }
                    // 四种运算符
                    for(int k = 0; k < 4; k++){
                        // 加法乘法符合交换律，i + j 和 j + i是一样的，当i < j的时候已经做过一次了
                        // 避免交换律重复计算
                        if(k < 2 && i > j){
                            continue;
                        }
                        // 加法
                        if(k == ADD){
                            listNext.add(n1 + n2);
                        }
                        // 减
                        else if(k == SUBTRACT){
                            listNext.add(n1 - n2);
                        }
                        // 乘
                        else if(k == MULTIPLY){
                            listNext.add(n1 * n2);
                        }
                        // 除
                        else if(k == DIVIDE){
                            // 判断除0的情况
                            if(Math.abs(n2) < ERROR){
                                continue;
                            }
                            else{
                                listNext.add(n1 / n2);
                            }
                        }
                        // 递归
                        // 得到结果了，直接返回
                        if(check(listNext)){
                            return true;
                        }
                        // 回溯，删除刚刚添加的元素
                        listNext.remove(listNext.size() - 1);
                    }
                }
            }
        }
        return false;
    }
}
