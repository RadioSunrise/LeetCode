// site: https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/submissions/

// 给定一个非负整型数组，返回一个数值最小的拼接字符串
// 按照给定的比较器来排序

package leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 剑指offer 45
 */
public class MinNumberLCOF {
    // 快排版本的
    public String minNumber(int[] nums){
        int n = nums.length;
        if(n == 0){
            return "";
        }
        String[] numStrs = new String[n];
        // 将数字转成字符串
        for(int i = 0; i < n; i++){
            numStrs[i] = String.valueOf(nums[i]);
        }
        // 按照给定的字典序排序(30 < 3)
        quickSortStr(numStrs, 0, n - 1);
        StringBuilder res = new StringBuilder();
        for(String str : numStrs){
            res.append(str);
        }
        return res.toString();
    }
    public void quickSortStr(String[] arr, int head, int rear){
        if(head >= rear){
            return;
        }
        int mid = partition(arr, head, rear);
        quickSortStr(arr, head, mid - 1);
        quickSortStr(arr, mid + 1, rear);
    }
    public int partition(String[] arr, int head, int rear){
        String pivot = arr[head];
        int j = rear;
        int i = head;
        // 按照给定的字典序排序，因为要30 < 3所以不能直接用compareTo
        // 可以用 30 + 3 = 303 < 330 = 3 + 30 来判断大小 
        while(i < j){
            while(i < j && (arr[j] + pivot).compareTo(pivot + arr[j]) >= 0){
                j--;
            }
            arr[i] = arr[j];
            while (i < j && (arr[i] + pivot).compareTo(pivot + arr[i]) <= 0){
                i++;
            }
            arr[j] = arr[i];
        }
        arr[i] = pivot;
        return i;
    }
    public static void main(String[] args){
        System.out.println("30".compareTo("3"));
        int[] nums = new int[] {3,30,34,5,9};
        String res = new MinNumberLCOF().minNumber(nums);
        System.out.println(res);
    }
}
// 直接用Arrays.sort也是可以的
public class MinNumberLCOF {
    public String minNumber(int[] nums){
        int n = nums.length;
        if(n == 0){
            return "";
        }
        String[] numStrs = new String[n];
        // 将数字转成字符串
        for(int i = 0; i < n; i++){
            numStrs[i] = String.valueOf(nums[i]);
        }
        // 按照给定的字典序排序(30 < 3)
        // 快排
        // quickSortStr(numStrs, 0, n - 1);
        // Arrays.sort + 比较器
        Arrays.sort(numStrs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1 + o2).compareTo(o2 + o1);
            }
        });
        StringBuilder res = new StringBuilder();
        for(String str : numStrs){
            res.append(str);
        }
        return res.toString();
    }
}
