https://leetcode-cn.com/problems/add-to-array-form-of-integer//

public class AddToArrayFormOfInteger {
    public List<Integer> addToArrayForm(int[] A, int K) {
        LinkedList<Integer> res = new LinkedList<>();
        int carry = 0;
        int n = A.length;
        int k = K;
        int i = n - 1;
        int num1 = 0;
        int num2 = 0;
        int sum = 0;
        while(k > 0 || carry != 0 || i >= 0){
            num1 = (i >= 0)? A[i] : 0;
            i--;
            num2 = (k > 0) ? k % 10 : 0;
            k = k / 10;
            sum = num1 + num2 + carry;
            carry = sum / 10;
            res.addFirst(sum % 10);
        }
        return res;
    }
}
