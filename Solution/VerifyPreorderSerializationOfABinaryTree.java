// site: https://leetcode-cn.com/problems/verify-preorder-serialization-of-a-binary-tree/

// 槽位 或者 出度入度统计

public class VerifyPreorderSerializationOfABinaryTree {
    public boolean isValidSerialization(String preorder) {
        // 槽位统计 等价于 出度入度的统计
        // 槽位：二叉树中有多少个位置待填充
        // 遇到 '#' 消耗一个槽位，即该位置填null
        // 遇到 数字 消耗一个槽位，因为节点非空，所以需要增加两个槽位，(左右孩子)

        // 可以用一个变量来统计槽位
        int len = preorder.length();
        int i = 0;
        int solt = 1;

        while(i < len){
            if(solt == 0){
                return false;
            }
            if(preorder.charAt(i) == ','){
                i++;
            } else if(preorder.charAt(i) == '#'){
                solt--;
                i++;
            } else {
                while(i < len && preorder.charAt(i) != ','){
                    i++;
                }
                // slot - 1 + 2
                solt++;
            }
        }
        return (solt == 0);
    }
}

class Solution {
    public boolean isValidSerialization(String preorder) {
        // 槽位统计 等价于 出度入度的统计
        // 槽位：二叉树中有多少个位置待填充
        // 遇到 '#' 消耗一个槽位，即该位置填null
        // 遇到 数字 消耗一个槽位，因为节点非空，所以需要增加两个槽位，(左右孩子)

        // 可以用一个变量来统计槽位
        // 也可以用一个栈

        // 栈的实现，栈顶元素是当前的槽位，等价于当前子树的槽位
        // 根节点特殊处理压入一个 1
        int len = preorder.length();
        Deque<Integer> stack = new LinkedList<>();
        stack.push(1);
        int i = 0;
        while(i < len){
            if(stack.isEmpty()){
                return false;
            }
            if(preorder.charAt(i) == ','){
                i++;
            } else if(preorder.charAt(i) == '#'){
                //消耗一个槽位
                int temp = stack.pop() - 1;
                // 如果temp > 0，再压回去，否则就出栈
                if(temp > 0){
                    stack.push(temp);
                }
                i++;
            } else {
                // 读一个数字
                while(i < len && preorder.charAt(i) != ','){
                    i++;
                }
                int temp = stack.pop() - 1;
                // 如果temp > 0，再压回去
                if(temp > 0){
                    stack.push(temp);
                }
                // 再压入一个 2，下一次遍历就处理这颗子树
                stack.push(2);
                // while 循环完其实i已经在下一个位置了，就不用再移动了
            }
        }
        // 如果栈空则ok，否则遍历完槽位还是剩就错了
        return stack.isEmpty();
    }
}

// 还可以用压缩的方式来做
// reference: https://leetcode-cn.com/problems/verify-preorder-serialization-of-a-binary-tree/solution/pai-an-jiao-jue-de-liang-chong-jie-fa-zh-66nt/
// 将有效的叶子节点用'#'代替，将 'x##' 变成 '#'
