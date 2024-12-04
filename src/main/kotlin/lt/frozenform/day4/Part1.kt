package lt.frozenform.day4

import lt.frozenform.common.Ext.Companion.string
import lt.frozenform.common.Input
import lt.frozenform.common.Matrix
import org.jooq.lambda.Seq
import java.util.*

fun main() {

    val matrix = Matrix.fromInput(Input(Main::class.java))

    println(matrix)

    println()
    matrix.rows().map { it.string() }.forEach(::println)
    println()
    matrix.cols().map { it.string() }.forEach(::println)
    println()
    matrix.diagsLR().map { it.string() }.forEach(::println)
    println()
    matrix.diagsRL().map { it.string() }.forEach(::println)

    var count = 0

    val xmas = "XMAS".toRegex()

    Seq.concat(matrix.rows(), matrix.cols(), matrix.diagsLR(), matrix.diagsRL())
        .map { it.string() }
        .forEach {
            count += xmas.findAll(it).count()
            count += xmas.findAll(it.reversed()).count()
        }

    println()
    println(count)
}
