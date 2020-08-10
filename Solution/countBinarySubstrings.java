// site: https://leetcode-cn.com/problems/count-binary-substrings/submissions/

// 找一个串里的符合条件的二进制串，采用分组统计的方式，思路见注释

// 第一版空间复杂度高
class Solution {
    public int countBinarySubstrings(String s) {
        // 按字符分组
        // 连续的0或1一组，统计每组的个数
        // 最后计算相邻两组的数量最小值，即为这两组能够贡献的子串个数
        // 比如说001110011 -> 统计的分组是{2,3,2,2}
        // 那么前两组的5个数00111能够贡献的个数是min{2,3} = 2
        // 后面同理
        List<Integer> countList = new ArrayList<>();
        int n = s.length();
        int count = 0;
        int index = 0;
        while(index < n){
            char c = s.charAt(index);
            count = 0;
            while(index < n && s.charAt(index) == c){
                ++index;
                ++count;
            }
            countList.add(count);
        }
        // 统计贡献
        int res = 0;
        for(int i = 1; i < countList.size(); i++){
            res += Math.min(countList.get(i - 1), countList.get(i));
        }
        return res;
    }
}

// 优化版的
class Solution {
    public int countBinarySubstrings(String s) {
        // 按字符分组
        // 连续的0或1一组，统计每组的个数
        // 最后计算相邻两组的数量最小值，即为这两组能够贡献的子串个数
        // 比如说001110011 -> 统计的分组是{2,3,2,2}
        // 那么前两组的5个数00111能够贡献的个数是min{2,3} = 2
        // 后面同理
        
        // 不用保存这个序列，因为每一个count只和前一个count有关，用prev表示前一个组的长度
        int n = s.length();
        int count = 0;
        int prev = 0;
        int res = 0;
        int index = 0;
        while(index < n){
            char c = s.charAt(index);
            count = 0;
            while(index < n && s.charAt(index) == c){
                ++index;
                ++count;
            }
            // 因为prev的初始值是0，所以第一次res必定是加0的，不用额外特判
            res +=  Math.min(prev, count);
            // 滚动
            prev = count;
        }
        return res;
    }
}
