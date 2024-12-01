package lt.frozenform.day1

import java.util.*
import kotlin.math.abs

fun main() {
    val left = mutableListOf<Int>()
    val right = mutableMapOf<Int, Int>()

    Scanner(Main::class.java.getResourceAsStream("input.txt")).use {
        while(it.hasNextLine()) {
            val parts = it.nextLine().split("\\s+".toRegex())
            left.add(parts[0].toInt())
            right.compute(parts[1].toInt()) { _, v -> v?.plus(1) ?: 1 }
        }
    }

    val sum = left
        .map { it * right.getOrDefault(it, 0) }
        .sum()

    println(sum)

}
