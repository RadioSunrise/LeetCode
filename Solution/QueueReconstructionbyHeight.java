// site: https://leetcode-cn.com/problems/queue-reconstruction-by-height/

// 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。

package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class QueueReconstructionbyHeight {
    public int[][] reconstructQueue(int[][] people) {
        // 贪心法
        // 因为把矮的人插在高的人前面是不会影响高的人的k值的，所以对于同样高的人，以k为下标插入队伍
        // 矮的人按照k插入队伍，高的人被移动不会导致k的改变，所以是正确的
        Arrays.sort(people, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                // 按身高从高到矮排序，身高相同则按位置从前到后排序
                if(o1[0] == o2[0]){
                    return o1[1] - o2[1];
                }
                else {
                    return o2[0] - o1[0];
                }
            }
        });
        List<int[]> queue = new LinkedList<>();
        for(int[] p : people){
            // 在p[1]的位置插入
            queue.add(p[1], p);
        }

        /*
        int n = people.length;
        int[][] res = new int[n][2];
        for(int i = 0; i < n; i++){
            int[] temp = queue.get(i);
            res[i][0] = temp[0];
            res[i][1] = temp[1];
        }
        */

        // 用toArray会快很多的，LinkedList内部通过node的next移动来实现toArray
        int n = people.length;
        return queue.toArray(new int[n][2]);
    }
}

// 2020-11-21 (2020-11-16)更新
class Solution {
    public int[][] reconstructQueue(int[][] people){
        int n = people.length;
        // 先排序
        // 不同身高按身高降序排，同身高按照k升序排
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] != o2[0]){
                    return o2[0] - o1[0];
                }
                else {
                    return o1[1] - o2[1];
                }
            }
        });
        // 用list的插入来实现向后“挤”
        List<int[]> queue = new LinkedList<>();
        for(int[] person : people){
            // 按照排位k来插入，前面插入矮的也不会影响k
            queue.add(person[1], person);
        }
        return queue.toArray(new int[n][2]);
    }
}
