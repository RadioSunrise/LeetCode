/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution {

    /**
    * BST 的中序遍历应用
    */ 

    var minVal: Int = Int.MAX_VALUE
    var prev: Int = -1

    fun minDiffInBST(root: TreeNode?): Int {
        if(root == null){
            return 0
        }
        inOrder(root)
        return minVal
    }

    /**
    * 中序遍历
    */
    fun inOrder(root: TreeNode?) {
        if(root == null){
            return
        }
        // 左子树
        inOrder(root.left)
        // 处理当前节点
        val curr: Int = root.`val`
        if(prev != -1){
            minVal = Math.min(minVal, Math.abs(prev - curr))
        }
        prev = curr
        // 右子树
        inOrder(root.right)
    }
}
