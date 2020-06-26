// site: https://leetcode-cn.com/problems/subsets/solution

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        for(int i = (int)Math.pow(2, n); (int)i < Math.pow(2, n + 1); i++)
        {
            List<Integer> temp = new ArrayList<>();
            //从获取2^n到2^(n+1)遍历，从1位获取子串的掩码
            String s = Integer.toBinaryString(i).substring(1); 
            int s_length = s.length();
            // System.out.println(s);
            for(int k = 0; k < s_length; k++)
            {
                if(s.charAt(k) == '1')
                {
                    temp.add(nums[k]);
                }
            }
            // System.out.println(temp);
            result.add(temp);
        }
        return result;
    }
}
// 用掩码的方式来选取元素，关键是掩码的生成，用移位等的技巧获取n位的字典序掩码串
