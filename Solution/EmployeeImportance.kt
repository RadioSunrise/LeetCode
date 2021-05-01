package Leetcode

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.HashMap

/**
 * https://leetcode-cn.com/problems/employee-importance/
 * DFS / BFS /
 * 统计某一个员工和他的下属（直属 + 非直属）的重要度
 */
class EmployeeImportance {
    /**
     * 因为题目给的是员工id，用HashMap存储 员工编号 和 员工对象
     * 方便查找
     */
    val map = HashMap<Int, Employee>()

    fun getImportance(employees: List<Employee?>, id: Int): Int {
        for (employee in employees) {
            if (employee != null) {
                map[employee.id] = employee
            }
        }

        // dfs
        // return dfs(id)

        // bfs
        return bfs(id)
    }

    /**
     * dfs搜索
     * 计算员工编号为[id]的员工和他的下属的重要度
     */
    private fun dfs(id: Int): Int {
        val employee = map[id]
        val subordinates = employee!!.subordinates
        // 初始化为员工自己的重要度
        var sum = employee.importance
        for (subordinate in subordinates) {
            sum += dfs(subordinate)
        }
        return sum
    }

    /**
     * bfs搜索
     */
    private fun bfs(id: Int): Int {
        var sum = 0
        val queue = LinkedList<Int>()
        queue.offer(id)
        while (!queue.isEmpty()) {
            val employeeId = queue.poll()
            val employee = map[employeeId]
            sum += employee!!.importance
            for (id in employee.subordinates) {
                queue.offer(id)
            }
        }
        return sum
    }
}