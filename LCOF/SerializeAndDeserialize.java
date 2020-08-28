/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 // 用层序遍历实现序列化和反序列化
 
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null){
            return "[]";
        }
        // 层次遍历
        StringBuilder res = new StringBuilder();
        res.append('[');
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            // 空则加入null
            if(node == null){
                res.append("null");
                res.append(',');
            }
            // 非空加入val值
            else {
                res.append(node.val);
                res.append(',');
                // 添加左右节点也要非空，看清楚序列的结构
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        // 删除最后一个逗号
        res.deleteCharAt(res.length() - 1);
        res.append(']');
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.length() == 0 || data.equals("[]")){
            return null;
        }
        // subString + split划分String
        String[] vals = data.substring(1, data.length() - 1).split(",");
        // 先创建根节点
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 0;
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            i++;
            // 左child节点
            if(!vals[i].equals("null")){
                TreeNode leftChild = new TreeNode(Integer.parseInt(vals[i]));
                node.left = leftChild;
                queue.offer(leftChild);
            }
            i++;
            // 右child节点
            if(!vals[i].equals("null")){
                TreeNode rightChild = new TreeNode(Integer.parseInt(vals[i]));
                node.right = rightChild;
                queue.offer(rightChild);
            }
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
