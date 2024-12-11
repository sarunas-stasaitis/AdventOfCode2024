package lt.frozenform.day11

import lt.frozenform.common.Input
import org.jooq.lambda.Seq.seq

fun main() {

    val start = System.currentTimeMillis()

    var stones: MutableList<Long> = seq(Input(Main::class.java).onlyLine().split(" "))
        .map { it.toLong() }
        .toList()

    val precompute = 25
    val iterations = 3

    val precomputed = mutableMapOf<Long, List<Long>>()
    for(i in 0L .. 9L) {
        precomputed[i] = computeSteps(i, precompute)
    }

    var totalStones = 0L
    for(stone in stones) {
        val childrenOfRock = computeStone2(stone, precomputed, iterations, precompute)
        totalStones += childrenOfRock
        println("Stone $stone has $childrenOfRock children")
    }

    println("Result: $totalStones")

    println("Execution time: ${System.currentTimeMillis() - start}ms")

}

private fun computeStone2(startingStone: Long, cache: MutableMap<Long, List<Long>>, iterations: Int, precompute: Int): Long {
    if (iterations == 1) {
        return cache.computeIfAbsent(startingStone) { computeSteps(it, precompute) }.size.toLong()
    }

    val thisLevel = cache.computeIfAbsent(startingStone) { computeSteps(it, precompute) }
    return thisLevel.sumOf { computeStone2(it, cache, iterations - 1, precompute) }
}

private fun computeSteps(stone: Long, count: Int): List<Long> {
    val stones = mutableListOf(stone)
    for (o in 0 until count) {
        var i = 0
        while (i < stones.size) {
            val st = stones[i]
            if (st == 0L) {
                stones[i] = 1
                i++
            } else if (digitCount(st) % 2 == 0) {
                val st = st.toString()
                val firstHalf = st.substring(0, st.length / 2).toLong()
                val secondHalf = st.substring(st.length / 2).toLong()
                stones[i] = firstHalf
                stones.add(i + 1, secondHalf)
                i += 2
            } else {
                stones[i] = stones[i] * 2024
                i++
            }
        }
    }

    println("Cached stone $stone has ${stones.size} children after $count steps")
    return stones
}