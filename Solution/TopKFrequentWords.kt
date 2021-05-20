package Leetcode

import android.os.Build
import android.telephony.emergency.EmergencyNumber
import androidx.annotation.RequiresApi
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


// site: https://leetcode-cn.com/problems/top-k-frequent-words/

class TopKFrequentWords {
    fun topKFrequent(words: Array<String>, k: Int): List<String> {
        // 统计词频，再按照字典序排序
        // Top K 问题用最小堆

        val map = HashMap<String, Int>()
        for (word in words) {
            if (!map.containsKey(word)) {
                map[word] = 1
            } else {
                map[word] = (map[word]!! + 1)
            }
        }

        // 最小堆
        val heap = PriorityQueue<WordCount>(k) { o1, o2 ->
            // 先按词频，再按字典序
            if (o1.count != o2.count) {
                o1.count - o2.count
            } else {
                // 倒序
                o2.word.compareTo(o1.word)
            }
        }

        for (entry in map) {
            // 不够 k 个直接入堆
            if (heap.size < k) {
                heap.add(WordCount(entry.key, entry.value))
            } else {
                // 和堆顶元素比较，词频大则替换
                val peek = heap.peek()
                if (entry.value > peek.count) {
                    heap.poll()
                    heap.add(WordCount(entry.key, entry.value))
                } else if (entry.value == peek.count) {
                    // 词频相同则比较字典序
                    if (entry.key < peek.word){
                        heap.poll()
                        heap.add(WordCount(entry.key, entry.value))
                    }
                }
            }
        }

        val res = ArrayList<String>()
        while (heap.isNotEmpty()) {
            res.add(heap.poll().word)
        }
        // 还要反过来，因为是小根堆
        res.reverse()
        return  res
    }

    inner class WordCount(val word: String, val count: Int)
}
