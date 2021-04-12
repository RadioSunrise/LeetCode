// site: https://leetcode-cn.com/problems/largest-number

// 将一个数组的数字重排，形成最大的数字
// 用自定义排序的方法实现
// 正确性证明：https://leetcode-cn.com/problems/largest-number/solution/gong-shui-san-xie-noxiang-xin-ke-xue-xi-vn86e/

public class LargestNumber {
    public String largestNumber(int[] nums) {
        // 自定义排序
        // 即 ab 和 ba 哪个比较大
        // 贪心法的思想
        int n = nums.length;
        String[] strs = new String[n];
        // 全部转成 String 类型
        for(int i = 0; i < n; i++){
            strs[i] = String.valueOf(nums[i]);
        }
        // lambda 表达式，自定义排序
        // 比较 ab 和 ba 哪个大
        Arrays.sort(strs, (a, b) -> {
            String aFirst = a + b;
            String bFirst = b + a;
            // 降序排，用 bFirst 调用 compareTo
            return bFirst.compareTo(aFirst);
        });
        // 将所有的字符串合并成一个串
        StringBuilder sb = new StringBuilder();
        for(String str : strs){
            sb.append(str);
        }
        // 处理前导 0
        int len = sb.length();
        // 找到第一个非 0 的位置
        int k = 0;
        while(k < len - 1 && sb.charAt(k) == '0'){
            k++;
        }
        // 从位置 k 截断
        return sb.substring(k);
    }
}
