// site: https://leetcode-cn.com/problems/assign-cookies/

// 贪心法实现

class Solution {
    public int findContentChildren(int[] g, int[] s) {
        // 贪心法
        Arrays.sort(g);
        Arrays.sort(s);
        // 从小到大遍历g，在s中找到最小的能够满足g[i]要求的s[j]
        int ng = g.length;
        int ns = s.length;
        int count = 0;
        for(int i = 0, j = 0; i < ng && j < ns; i++, j++){
            // 逐个找最小的s[j]
            while(j < ns && s[j] < g[i]){
                j++;
            }
            if(j < ns){
                count++;
            }
        }
        return count;
    }
}
