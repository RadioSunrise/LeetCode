// site: https://leetcode-cn.com/problems/range-sum-of-bst/

// 注意用中序不是最快的，全部遍历完要O(n)，可以用dfs，利用BST的性质剪枝，可以减小时间复杂度

class RangSumOfBST {
    /**
    * 中序遍历没有用到BST的特性，可以用dfs，时间复杂度可以进一步减小
    * 根据当前节点的值判断应该返回那一边的值
    */

    fun rangeSumBST(root: TreeNode?, low: Int, high: Int): Int {
        return dfs(root, low, high)
    }

    fun dfs(node: TreeNode?, low: Int, high: Int): Int {
        if (node == null) {
            return 0
        }
        when {
            // 当前节点的值小于 low，则本节点的左边都不再需要考虑，转向右子树
            (node.`val` < low) -> return dfs(node.right, low, high)
            // 当前节点的值大于 high，右边都不再需要考虑，转向左边
            (node.`val` > high) -> return dfs(node.left, low, high)
            // 若处于[low, high]的范围，则加上本身和两边的值
            else -> return node.`val` + dfs(node.left, low, high) + dfs(node.right, low, high)
        }
    }
}
