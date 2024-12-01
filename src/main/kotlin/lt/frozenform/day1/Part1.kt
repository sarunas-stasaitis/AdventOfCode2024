package lt.frozenform.day1

import java.util.*
import kotlin.math.abs

fun main() {
    val left = mutableListOf<Int>()
    val right = mutableListOf<Int>()

    Scanner(Main::class.java.getResourceAsStream("input.txt")).use {
        while(it.hasNextLine()) {
            val parts = it.nextLine().split("\\s+".toRegex())
            left.add(parts[0].toInt())
            right.add(parts[1].toInt())
        }
    }

    left.sort()
    right.sort()

    val sum = left.zip(right)
        .map { abs(it.second - it.first) }
        .sum()

    println(sum)
}
