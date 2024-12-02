package lt.frozenform.day2

import java.util.*
import kotlin.math.abs
import kotlin.math.sign

fun main() {

    var safeLines = 0

    Scanner(Main::class.java.getResourceAsStream("input.txt")!!).use {
        reportCheck@ while (it.hasNextLine()) {
            val reports = it.nextLine().split("\\s+".toRegex())
                .map { it.toDouble() }

            var dir = 0.0

            for (i in 1 until reports.size) {
                val v1 = reports[i - 1]
                val v2 = reports[i]
                val dif = v1 - v2
                if (dir == 0.0) {
                    dir = sign(dif)
                    if (dir == 0.0) {
                        continue@reportCheck
                    }
                } else {
                    if (sign(dif) != dir) {
                        continue@reportCheck
                    }
                }
                if (abs(dif) > 3) {
                    continue@reportCheck
                }

            }
            safeLines++
        }
    }

    println(safeLines)
}
