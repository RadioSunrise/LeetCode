// site: https://leetcode-cn.com/problems/decode-xored-permutation/

class DecodeXoredPermutation {
    public int[] decode(int[] encoded) {
        // 利用奇偶性质，和 perm 是 n 的排列
        int n = encoded.length + 1;
        int total = 0;
        for (int i = 1; i <= n; i++) {
            total = total ^ i;
        }
        // 计算 odd, 即所有 encoded 奇数元素的异或，为 perm[1] XOR .. XOR perm[n - 1]
        int odd = 0;
        for (int i = 1; i < n - 1; i+=2) {
            odd = odd ^ encoded[i];
        }
        int[] perm = new int[n];
        // 计算 perm[0]
        perm[0] = total ^ odd;
        // 迭代计算 perm
        // 关系式 perm[i + 1] = perm[i] ^ encoded[i];
        for (int i = 0; i < n - 1; i++) {
            perm[i + 1] = perm[i] ^ encoded[i];
        }
        return perm;
    }
}
