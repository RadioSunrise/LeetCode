// site: https://leetcode-cn.com/problems/cousins-in-binary-tree/

// 堂兄弟节点，DFS 和 BFS 两种实现方法

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
class SolutionDFS {
    // 深度优先搜索，递归函数带上深度和父节点，用于判断
    
    // 类变量
    var depthX = 0
    var depthY = 0
    var parentX: TreeNode? = null
    var parentY: TreeNode? = null
    var findX = false
    var findY = false
    var valueX = 0
    var valueY = 0

    fun isCousins(root: TreeNode?, x: Int, y: Int): Boolean {
        // dfs 搜索
        valueX = x
        valueY = y
        dfs(root, 0, null)
        // 层数相同但父节点不同
        return (depthX == depthY) && (parentX != parentY)
    }

    // 先序 dfs
    fun dfs(root: TreeNode?, depth: Int, parent: TreeNode?) {
        if (root == null) {
            return
        }
        // 判断当前节点
        if (root.`val` == valueX) {
            findX = true
            parentX = parent
            depthX = depth
        }
        if (root.`val` == valueY) {
            findY = true
            parentY = parent
            depthY = depth
        }
        // 都找到了，则返回
        if (findX && findY) {
            return
        }
        // 左右孩子
        dfs(root.left, depth + 1, root)
        dfs(root.right, depth + 1, root)
    }
}


class SolutionBFS {
    // 广度优先搜索
    
    // 类变量
    var depthX = 0
    var depthY = 0
    var parentX: TreeNode? = null
    var parentY: TreeNode? = null
    var findX = false
    var findY = false
    var valueX = 0
    var valueY = 0

    fun isCousins(root: TreeNode?, x: Int, y: Int): Boolean {
        if(root == null) {
            return false
        }
        // dfs 搜索
        valueX = x
        valueY = y
        bfs(root)
        // 层数相同但父节点不同
        return (depthX == depthY) && (parentX != parentY)
    }

    // bfs
    fun bfs(root: TreeNode) {
        val queue: Queue<TreeNodeDepth> = LinkedList<TreeNodeDepth>()
        queue.add(TreeNodeDepth(root, 0))
        while(!queue.isEmpty()) {
            val nodeDepth = queue.poll()!!
            val node = nodeDepth.node!!
            val depth = nodeDepth.depth!!
            if (node.left != null) {
                queue.add(TreeNodeDepth(node.left, depth + 1))
                update(node.left, depth + 1, node)
            }
            if (node.right != null) {
                queue.add(TreeNodeDepth(node.right, depth + 1))
                update(node.right, depth + 1, node)
            }
            if (findX && findY) {
                break;
            }
        }
    }

    /**
    * 内部类 TreeNodeDepth
    */
    inner class TreeNodeDepth(val node: TreeNode?, val depth: Int)

    /**
    * 辅助更新类变量的函数
    */
    fun update(node: TreeNode, depth: Int, parent: TreeNode) {
        if (node.`val` == valueX) {
            findX = true
            depthX = depth
            parentX = parent
        }
        if (node.`val` == valueY) {
            findY = true
            depthY = depth
            parentY = parent
        }
    }
}
