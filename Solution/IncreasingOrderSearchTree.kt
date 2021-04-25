// site: https://leetcode-cn.com/problems/increasing-order-search-tree/
// 中序遍历的应用
// 注意函数返回类型的设置，通过prev来遍历和连接，不需要遍历函数来返回


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
class IncreasingOrderSearchTree {

    lateinit var prev: TreeNode

    fun increasingBST(root: TreeNode?): TreeNode? {
        if (root == null) {
            return null
        }
        val dummyRoot = TreeNode(-1)
        prev = dummyRoot
        inOrderTrival(root)
        return dummyRoot.right
    }

    /**
    * 中序遍历
    * 遍历的过程中修改节点指向
    */ 
    fun inOrderTrival(node: TreeNode?) {
        if (node == null) {
            return
        }
        // 左孩子
        inOrderTrival(node.left)

        // prev是左孩子及之前遍历完的最后一个节点，从node开始剩下的要接到prev的右孩子
        prev.right = node
        node.left = null
        prev = node

        // 右孩子
        inOrderTrival(node.right)
    }
}
