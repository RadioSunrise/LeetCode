// site: https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 // 递归方法，树的深度等于max(左子树深度,右子树深度) + 1，叶子节点则为0
 // DFS
class Solution_DFS {
    public int maxDepth(TreeNode root) {
       if(root == null)
       {
           return 0;
       }
       int left_deepth = maxDepth(root.left);
       int right_deepth = maxDepth(root.right);
       return Math.max(left_deepth, right_deepth) + 1;
    }
}
 // BFS
class Solution_BFS {
    public int maxDepth(TreeNode root) {
        if(root == null)
        {
           return 0;
        }
        int depth = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(! q.isEmpty())
        {
            int q_size = q.size(); // 获取当前队长(同一层点的个数)
            while(q_size > 0)
            {
                TreeNode temp = q.poll();
                if(temp.left != null) //左子树不空，入队
                {
                    q.add(temp.left);
                }
                if(temp.right != null) //右子树不空，入队
                {
                    q.add(temp.right);
                }
                q_size--;
            }
            depth ++; //同一层的点全部出队了再depth++，不要重复增加深度
        }
        return depth;
    }
}

