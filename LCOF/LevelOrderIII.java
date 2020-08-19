/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 // 层次打印二叉树，偶数层要反序，可以通过LinkedList的addFirst和addLast来控制添加的顺序
 
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if(root == null){
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 1;
        while(!queue.isEmpty()){
            depth++;
            int size = queue.size();
            // 用LinkedList
            LinkedList<Integer> layer = new LinkedList<>();
            for(int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                // 奇数层，加到layer的尾部（等价于add）
                if(depth % 2 == 0){
                    layer.addLast(node.val);
                }
                // 偶数层，加到layer的头部
                else if(depth % 2 == 1){
                    layer.addFirst(node.val);
                }
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            res.add(layer);
        }
        return res;
    }
}
