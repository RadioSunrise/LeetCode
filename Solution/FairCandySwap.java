// site: https://leetcode-cn.com/problems/fair-candy-swap/submissions/

// 要找的数是a0和b0，分别属于A和B
// sumA - a0 + b0 = sumB - b0 + a0 => (sumA - sumB)/2 = a0 - b0 => a0 = b0 + (sumA - sumB)/2
// 所以对于B中的任意一个数b，只要A中存在一个数满足上面的条件即可
// 用哈希实现
class Solution {
    public int[] fairCandySwap(int[] A, int[] B) {
        // a0 = b0 + (sumA - sumB)/2
        // 用哈希set实现
        int sumA = 0;
        int sumB = 0;
        Set<Integer> set = new HashSet<>();
        for(int a : A){
            sumA += a;
            set.add(a);
        }
        for(int b : B){
            sumB += b;
        }
        int diff = (sumA - sumB) / 2;
        int[] res = new int[2];
        // 遍历B
        for(int b : B){
            int target = b + diff;
            if(set.contains(target)){
                res[0] = target;
                res[1] = b;
                break;
            }
        }
        return res;
    }
}


// 双指针的实现
// 排序要NlogN，不是很快
class Solution {
    public int[] fairCandySwap(int[] A, int[] B) {
        // 计算 A 和 B 的总和，计算出答案对的差值
        // 排序之后双指针寻找
        int sumA = 0;
        int sumB = 0;
        for(int a : A){
            sumA += a;
        }
        for(int b : B){
            sumB += b;
        }
        // 需要找到的两个数的差值
        // 不需要求abs，后续求差的顺序一样就可以了，不然还有分类讨论
        int diff = (sumA - sumB) / 2;
        // 两个数组排序
        Arrays.sort(A);
        Arrays.sort(B);
        // 双指针找答案，只往一个方向走
        int pointerA = 0;
        int pointerB = 0;
        while(pointerA < A.length && pointerB < B.length){
            int curr = A[pointerA] - B[pointerB];
            if(curr == diff){
                return new int[] {A[pointerA], B[pointerB]};
            } else if (curr < diff){
                // 减人的不够大
                pointerA++;
            } else {
                // 被减的太小了
                pointerB++;
            }
        }
        return new int[]{0, 0};
    }
}
