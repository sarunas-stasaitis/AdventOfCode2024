package lt.frozenform.day11

import lt.frozenform.common.Input
import org.jooq.lambda.Seq.seq

fun main() {

    val stones: MutableList<Long> = seq(Input(Main::class.java).onlyLine().split(" "))
        .map { it.toLong() }
        .toList()

    for(o in 0 until 25) {
        var i = 0
        while (i < stones.size) {
            val st = stones[i].toLong()
            if (st == 0L) {
                stones[i] = 1
                i++
            } else if (digitCount(st) %2 == 0) {
                val st = st.toString()
                val firstHalf = st.substring(0, st.length / 2).toLong()
                val secondHalf = st.substring(st.length / 2).toLong()
                stones[i] = firstHalf
                stones.add(i + 1, secondHalf)
                i+=2
            } else {
                stones[i] = stones[i] * 2024
                i++
            }
        }

    }

    println(stones.size)

}

fun digitCount(n: Long): Int {
    var count = 0
    var num = n
    while (num != 0L) {
        num /= 10
        count++
    }
    return count
}