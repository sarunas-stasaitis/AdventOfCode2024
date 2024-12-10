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
        val visited = mutableSetOf<Point>()
        step(start, visited, listOf(start.point()), m) { path ->
            println("    $path")
        }
        val cnt = visited.count { m[it].digitToInt() == 9 }
        sum += cnt
        println(cnt)
    }

    println()
    println("Sum: $sum")

}

fun step(
    currentPos: Matrix.Cell,
    visited: MutableSet<Point>,
    currentPath: List<Point>,
    m: Matrix,
    fullPathConsumer: (List<Point>) -> Unit
) {
    if (!visited.add(currentPos.point())) {
        return
    }

    val height = currentPos.digitValue()
    val neighbours = m.neighbours4(currentPos.point())
        .values
        .filter { it.digitValue() == height + 1 }

    for (neighbour in neighbours) {
        if (neighbour.digitValue() == 9) {
            fullPathConsumer(currentPath + neighbour.point())
            visited.add(neighbour.point())
        } else {
            step(neighbour, visited, currentPath + neighbour.point(), m, fullPathConsumer)
        }
    }

}