// site: https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
// 用两个栈实现队列

public class CQueue
{
    Stack<Integer> s1;
    Stack<Integer> s2;
    public CQueue()
    {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }
    public void appendTail(int value)
    {
        s1.push(value);
    }

    public int deleteHead()
    {
        int res;
        int temp;
        if(s1.isEmpty() && s2.isEmpty())
        {
            return -1;
        }
        if(s2.isEmpty()) //当s2为空才把s1的元素移到s2里面去
        {
            while(!s1.isEmpty())
            {
                temp = s1.pop();
                s2.push(temp);
            }
        }
        // s2不为空，直接pop就可以了
        res = s2.pop();
        return res;
    }
}
// 关键是两个栈s1用于输入，s2用于输出。只有当s2为空才会把s1的元素倒过去，否则直接输出。
// 因为逆序的逆序就是正序，所以在s2里面的排列是正确的队列顺序，s2不为空s1就不要倒过去
