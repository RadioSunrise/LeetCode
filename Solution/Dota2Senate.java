// site: https://leetcode-cn.com/problems/dota2-senate/submissions/

// 通过队列来模拟这个过程，变相实现循环队列，关键是再次入队时，+n的操作

class Solution {
    public String predictPartyVictory(String senate) {
        // 用队列来模拟这个过程
        int n = senate.length();
        Queue<Integer> Dire = new LinkedList<>();
        Queue<Integer> Radiant = new LinkedList<>();
        // 按顺序入队
        for(int i = 0; i < n; i++){
            if(senate.charAt(i) == 'R'){
                Radiant.offer(i);
            }
            else {
                Dire.offer(i);
            }
        }
        // 优先沉默离自己最近的敌方成员
        // 沉默用出队来表示，当某一方的队列清空，则另一方获胜
        while(!Radiant.isEmpty() && !Dire.isEmpty()){
            // 弹出两边的队首成员，判断谁先出手
            int direIndex = Dire.poll();
            int radiantIndex = Radiant.poll();
            // 先出手的一方沉默另一方，同时再次入队进入下一轮，用+n来表示
            // +n可以让这个人进入下一轮或者在这一轮被另一方沉默
            // 变相实现“循环”队列
            if(direIndex < radiantIndex){
                Dire.offer(direIndex + n);
            }
            else {
                Radiant.offer(radiantIndex + n);
            }
            // 另一方就永久出队了
        }
        return !Radiant.isEmpty() ? "Radiant" : "Dire";
    }
}
