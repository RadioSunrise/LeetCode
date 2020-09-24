// site: https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/

// 在BST中找众数
// 由于是一棵BST，所以中序遍历是有序的，在有序序列中，相同的数字连续出现，所以通过当前数和前一个数比较，就可以计算出这个数出现多少次了

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 // 一般的中序遍历做法
class Solution {
    int preVal;
    int count;
    int maxCount;
    public int[] findMode(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inOrder(root, res);
        int[] ans = new int[res.size()];
        for(int i = 0; i < res.size(); i++){
            ans[i] = res.get(i);
        }
        return ans;
    }
    public void inOrder(TreeNode node, List<Integer> res){
        if(node == null){
            return;
        }
        inOrder(node.left, res);

        // 当前值和前一个节点值是否相等
        if(node.val == preVal){
            count++;
        }
        else {
            // 更新count和preVal
            count = 1;
            preVal = node.val;
        }
        if(count == maxCount){
            res.add(node.val);
        }
        else if(count > maxCount){
            maxCount = count;
            res.clear();
            res.add(node.val);
        }
        
        inOrder(node.right, res);
    }
}

// Morris中序遍历 
class Solution {
    int base;
    int count;
    int maxCount;
    List<Integer> res = new ArrayList<>();
    public int[] findMode(TreeNode root) {
        TreeNode curr = root;
        TreeNode prev = null;
        while(curr != null){
            // 左孩子为空，直接处理当前节点
            if(curr.left == null){
                update(curr.val);
                curr = curr.right;
            }
            else {
                // 当前节点的前驱是左子树的最右边的节点
                prev = curr.left;
                // 向右走，且right不能等于curr，否则会走回去curr的
                while(prev.right != null && prev.right != curr){
                    prev = prev.right;
                }
                // 左子树的最右节点还没被修改过
                if(prev.right == null){
                    // 修改右指针为当前节点
                    prev.right = curr;
                    // curr 转向左边
                    curr = curr.left;
                }
                // 已经修改过了，即已经修改成root了
                else {
                    // 修改回null
                    prev.right = null;
                    // 处理当前节点
                    update(curr.val);
                    // 转向右子树
                    curr = curr.right;
                }
            }
        }
        int[] mode = new int[res.size()];
        for(int i = 0; i < res.size(); i++){
            mode[i] = res.get(i);
        }
        return mode;
    }
    /**
    * update函数，Morris中序遍历的处理函数
    */
    public void update(int val){
        if(val == base){
            count++;
        }
        else {
            count = 1;
            base = val;
        }
        if(count == maxCount){
            res.add(val);
        }
        if(count > maxCount){
            maxCount = count;
            res.clear();
            res.add(val);
        }
    }
}
