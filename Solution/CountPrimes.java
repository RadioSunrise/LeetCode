// site: 

// 给定1个正整数n，统计小于n的素数个数

// 暴力法，每个数 x∈[2, n - 1]都判断，每个x判断到sqrt(x)是否为因子
// O(n*sqrt(n))
class Solution {
    public int countPrimes(int n) {
        int ans = 0;
        // 暴力法，2到n-1都判断
        for(int i = 2; i < n; i++){
            if(isPrime(i)){
                ans++;
            }
        }
        return ans;
    }
    /**
    * 判断x是否为质数
    */ 
    public boolean isPrime(int x){
        for(int i = 2; i * i <= x; i++){
            if(x % i == 0){
                return false;
            }
        }
        return true;
    }
}

// 埃氏筛，用数组标记不行的值，x是质数，则2x,3x,...都不是，主要从x*x开始标记，前面的2x、3x...会被前面的数标记
// O(nloglogn)
class Solution {
    public int countPrimes(int n) {
        int ans = 0;
        // 埃氏筛2x3x...x2x,3x*x
        // 若x是质数，则2x,3x,4x...都不是质数
        // 用数组记录
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        for(int i = 2; i < n; i++){
            if(isPrime[i] == 1){
                ans++;
                // 从i * i开始标记（之前的2*i，3*i会被2和3等标记)
                // 转long
                if((long) i * i < n){
                    // 从i开始，每次加i，标记
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = 0;
                    }
                }
            }
        }
        return ans;
    }
}

// O(n)的线性积待学习
