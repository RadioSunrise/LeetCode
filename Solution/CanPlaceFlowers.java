// site: https://leetcode-cn.com/problems/can-place-flowers/

// 种花问题

// 第一版有边界条件的判断，判断每个位置是否可以种花，比较麻烦
class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if(n == 0){
            return true;
        }
        int len = flowerbed.length;
        if(len == 1){
            if(flowerbed[0] == 0 && n <= 1){
                return true;
            }
            else {
                return false;
            }
        }
        // 能种个个数
        int count = 0;
        for(int i = 0; i < len; i++){
            if(i == 0 && (flowerbed[i] + flowerbed[i + 1] == 0)){
                flowerbed[i] = 1;
                count++;
            }
            else if(i > 0 && i < (len - 1) && (flowerbed[i - 1] + flowerbed[i] + flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                count++;
            }
            else if((i == (len - 1)) && (flowerbed[i - 1] + flowerbed[i] == 0)) {
                flowerbed[i] = 1;
                count++;
            }
            if(count >= n){
                return true;
            }
        }
        return false;
    }
}

// 模拟在数组两边加0，解决边界问题
// 用numZero记录连续的0的个数，初始化为1（假设最左边加1个0）
class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if(n == 0){
            return true;
        }
        // count 是能种花的数量; numZero 是连续的0的个数，初始化为1
        int count = 0;
        int numZero = 1;
        // 遍历
        for(int i = 0; i < flowerbed.length; i++){
            // 如果当前位置为0，则增加连续0的个数，否则置0
            if(flowerbed[i] == 0){
                numZero++;
            }
            else {
                numZero = 0;
            }
            // 凑够连续3个0则种一朵花，在中间位置种的，所以numZero = 1
            if(numZero == 3){
                count++;
                numZero = 1;
                if(count >= n){
                    return true;
                }
            }
        }
        // 遍历完的时候，走到最后一个位置，此时只需要 numZero = 2 就可以种了
        if(numZero == 2){
            count++;
        }
        return count >= n;
    }
}
