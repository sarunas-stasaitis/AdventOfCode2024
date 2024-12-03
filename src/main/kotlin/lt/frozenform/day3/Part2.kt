package lt.frozenform.day3

import java.util.*
import kotlin.math.abs
import kotlin.math.sign

fun main() {

    var enabled = true
    var total = 0

    Scanner(Main::class.java.getResourceAsStream("input.txt")!!).use {
        while (it.hasNextLine()) {
            val line = it.nextLine()

            val r = "do\\(\\)|don't\\(\\)|mul\\((\\d{1,3}),(\\d{1,3})\\)".toRegex()
            r.findAll(line).forEach {
                val value = it.value
                if (value.startsWith("don't")) {
                    enabled = false
                } else if (value.startsWith("do")) {
                    enabled = true
                } else if (enabled) {
                    val (a, b) = it.destructured
                    val intA = a.toInt()
                    val intB = b.toInt()
                    println("mul($a, $b) = ${intA * intB}")

                    total += intA * intB
                } else {
                    println("Skipping $value")
                }
            }
        }
    }



    println("Total: $total")

}
