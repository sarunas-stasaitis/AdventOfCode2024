package lt.frozenform.day6

import lt.frozenform.common.CardinalDirection4.N
import lt.frozenform.common.Input
import lt.frozenform.common.Matrix
import lt.frozenform.common.Point

fun main() {

    val visited = mutableSetOf<Point>()

    val m = Matrix.fromInput(Input(Main::class.java))

    var location = m.find { it.value == '^' }!!.point()

    visited.add(location)
    println(location)

    var direction = N
    while(true) {
        var next = location + direction
        if(!m.contains(next)) {
            break
        }
        while (m[next] == '#') {
            direction = direction.cw()
            next = location + direction
        }
        location = next

        println(next)
        visited.add(next)
    }

    println()
    println(visited.size)

}