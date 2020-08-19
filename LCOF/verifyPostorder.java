// site: https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/submissions/

// 判断一个序列是不是BST的后序遍历

// 递归的方法
// 后序遍历的序列分为三部分[左子树 | 右子树 | 根]
// 如果是BST的后序遍历序列，那么左子树部分比根小，右子树部分比根大
// 所以从左到右遍历递归区间[i, j]，找到第1个比根大的数，以此为界划分开左子树和右子树
// 如果右子树部分存在比根节点小的数，就表明false，（左子树部分在找分界的时候已经判断过了）
class Solution {
    public boolean verifyPostorder(int[] postorder) {
        return recurCheck(postorder, 0, postorder.length - 1);

    }
    public boolean recurCheck(int[] postorder, int i, int j){
        // 结束条件，如果结点数小于等于1返回true
        if(i >= j){
            return true;
        }
        int k = i;
        while(postorder[k] < postorder[j]){
            k++;
        }
        int m = k;
        // 判断右子树部分是不是均大于根节点
        // 通过遍历的方式
        while(postorder[k] > postorder[j]){
            k++;
        }
        if(k != j){
            // 右子树部分有比根节点小的数字
            // 返回false
            return false;
        }
        // 递归
        return recurCheck(postorder, i, m - 1) && recurCheck(postorder, m, j - 1);
    }
}

// 单调栈的做法
class Solution {
    public boolean verifyPostorder(int[] postorder) {
        // 单调栈
        // 后序遍历序列的逆序，是[根 | 右 | 左]
        // 所以逆序遍历后序序列的时候，如果开始变小，则说明进入左子树
        // 通过栈来保存这个左子树节点的根节点，即保存剩余序列值的上界
        // 如果在剩下的没遍历过的序列中出现了大于根节点的数，则false

        // 初始化的是总的根节点是+无穷
        Stack<Integer> stack = new Stack<>();
        int root = Integer.MAX_VALUE;
        // 逆序遍历postorder
        for(int i = postorder.length - 1; i >=0; i--){
            // 左子树有数字超过了根节点
            if(postorder[i] > root){
                return false;
            }
            // 找到当前节点的根节点
            // 栈不为空且当前数字小于栈顶
            while(!stack.isEmpty() && stack.peek() > postorder[i]){
                System.out.println("Go to left, current value is " + postorder[i] + ", and peek() is " + stack.peek());
                root = stack.pop();
            }
            System.out.println("root is " + root);
            // 当前元素入栈
            stack.push(postorder[i]);
        }
        return true;
    }
}
