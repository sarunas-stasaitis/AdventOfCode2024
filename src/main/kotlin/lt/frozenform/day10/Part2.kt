package lt.frozenform.day10

import lt.frozenform.common.Input
import lt.frozenform.common.Matrix
import lt.frozenform.common.Point

fun main() {

    val m = Matrix.fromInput(Input(Main::class.java))

    val starts = m.filter { it.value == '0' }

    var sum = 0

    for (start in starts) {
        println("Start: $start")
        var cnt = 0
        step(start, listOf(start.point()), m) { path ->
            println("    $path")
            cnt++
        }
        sum += cnt
        println(cnt)
    }

    println()
    println("Sum: $sum")

}

fun step(
    currentPos: Matrix.Cell,
    currentPath: List<Point>,
    m: Matrix,
    fullPathConsumer: (List<Point>) -> Unit
) {
    val height = currentPos.digitValue()
    val neighbours = m.neighbours4(currentPos.point())
        .values
        .filter { it.digitValue() == height + 1 }

    for (neighbour in neighbours) {
        if (neighbour.digitValue() == 9) {
            fullPathConsumer(currentPath + neighbour.point())
        } else {
            step(neighbour, currentPath + neighbour.point(), m, fullPathConsumer)
        }
    }

}