package leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * site: https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
 * 锯齿形层序遍历，在原本层序遍历的基础上增加双端队列的结构，用双端队列来处理结果存入的顺序
 * 原本层次遍历的结构不变
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if(root == null){
            return res;
        }
        // 遍历还是按照层次遍历bfs来，使用队列实现
        // 但是存入结果的时候根据层序来存入结果，用双端队列实现
        // 用两个数据结构来实现
        boolean LeftOrder = true;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            // 用于存入本层结果的双端队列
            Deque<Integer> layer = new LinkedList<>();
            int n = queue.size();
            for(int i = 0; i < n; i++){
                TreeNode node = queue.poll();
                // 处理node，按照层序存入结果
                if(LeftOrder){
                    layer.offerLast(node.val);
                }
                else {
                    layer.offerFirst(node.val);
                }
                // 处理层次遍历，按bfs实现
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            // 加入res，次序反向
            res.add(new LinkedList<>(layer));
            LeftOrder = !LeftOrder;
        }
        return res;
    }
    public static void main(String[] args){
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        List<List<Integer>> res = new BinaryTreeZigzagLevelOrderTraversal().zigzagLevelOrder(root);
        System.out.println(res.toString());
    }
}
