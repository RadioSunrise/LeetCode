package leetcode;

import edu.princeton.cs.algs4.In;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？
 * 请你找出所有满足条件且不重复的三元组。
 */

/**
 * 从暴力法的三重循环改进
 * (1) 去重 -- 使用排序来进行去重
 * (2) 减少一层循环 -- 因为a + b + c = 0，所以当a b 定下来的是c就是唯一的，当第二层遇到一个b' > b，则有c' < c
 *     所以把第二和第三层层合并成双指针
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        // 从暴力法的三种循环改进
        // 排序
        Arrays.sort(nums);
        int len = nums.length;
        if(len < 3){
            return res;
        }
        // 根据排序可以排除均大于0或者均小于0的情况
        if(nums[0] * nums[len - 1] > 0){
            return res;
        }
        for(int first = 0; first < len - 2; first++){
            // 为了排除重复，需要和上一次的枚举的数不同
            if(first > 0 && nums[first] == nums[first - 1]){
                continue;
            }
            // 两个指针
            int second = first + 1;
            int third = len - 1;
            int target = -nums[first];
            // 第二层循环 -- 双指针，可以先将nums[first]的相反数存起来，找nums[second] + nums[third] = target方便比较
            for(; second < len; second++){
                // second的选择和上次不一样，且要比first + 1大
                if(second > first + 1 && nums[second] == nums[second - 1]){
                    continue;
                }
                // 固定second，third向second靠近，这样在second增加（右移）的时候，third会继续左移，不用从尾部开始
                // third往左移的条件是当前数值太大了，要减小
                while(second < third && (nums[second] + nums[third] > target)){
                    third--;
                }
                // 如果碰到了，下一轮的second选择
                if(second == third){
                    continue;
                }
                // 找到一组解
                if(nums[second] + nums[third] == target){
                    LinkedList<Integer> temp = new LinkedList<>();
                    temp.add(nums[first]);
                    temp.add(nums[second]);
                    temp.add(nums[third]);
                    res.add(temp);
                }
                // nums[second] + nums[third] < target的，直接下一轮的second增加
            }
        }
        return res;
    }
    public static void main(String[] args){
        int[] nums = new int[] {-1, -1, 0, -1, 0, 100, 0, 1, 2, -1, -4};
        List<List<Integer>> res = new ThreeSum().threeSum(nums);
        System.out.println(res.toString());
    }
}

// 传统双指针的写法
package leetcode;

import edu.princeton.cs.algs4.In;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？
 * 请你找出所有满足条件且不重复的三元组。
 */

/**
 * 从暴力法的三重循环改进
 * (1) 去重 -- 使用排序来进行去重
 * (2) 减少一层循环 -- 因为a + b + c = 0，所以当a b 定下来的是c就是唯一的，当第二层遇到一个b' > b，则有c' < c
 *     所以把第二和第三层层合并成双指针
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        // 从暴力法的三种循环改进
        // 排序
        Arrays.sort(nums);
        int len = nums.length;
        if(len < 3){
            return res;
        }
        // 根据排序可以排除均大于0或者均小于0的情况
        if(nums[0] * nums[len - 1] > 0){
            return res;
        }
        for(int first = 0; first < len - 2; first++){
            // 为了排除重复，需要和上一次的枚举的数不同
            if(first > 0 && nums[first] == nums[first - 1]){
                continue;
            }
            // 两个指针
            int second = first + 1;
            int third = len - 1;
            int target = -nums[first];
            // 第二层循环 -- 双指针
            while(second < third){
                // 找到了
                int temp = nums[second] + nums[third];
                if(temp == target){
                    res.add(Arrays.asList(nums[first], nums[second], nums[third]));
                    // 移动
                    while(second < third && nums[second] == nums[++second]){}
                    while(second < third && nums[third] == nums[--third]){}
                }
                // 小了，左指针前移
                else if(temp < target){
                    // 移动到一个不重复的位置位置
                    while (second < third && nums[second] == nums[++second]){ }
                }
                else if(temp > target){
                    while (second < third && nums[third] == nums[--third]){ }
                }
            }
        }
        return res;
    }
    public static void main(String[] args){
        int[] nums = new int[] {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> res = new ThreeSum().threeSum(nums);
        System.out.println(res.toString());
    }
}
