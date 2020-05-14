# twoSum
class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        unordered_map<int,int> MyMap;
        for(int i = 0; i < nums.size(); i++)
        {
            int diff = target - nums[i];
            if(MyMap.find(diff) != MyMap.end())
            {
                return {MyMap[diff], i};
            }
            MyMap[nums[i]] = i;
        }
        return {};
    }
};
# 求和转换成求差
# 用map结构来存放已经遍历过的数和索引，用map/unordered的find来进行查找，可以减少一层循环
# map结构的用法和map的思想
