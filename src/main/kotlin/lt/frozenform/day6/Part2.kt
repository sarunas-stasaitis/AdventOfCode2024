package lt.frozenform.day6

import lt.frozenform.common.CardinalDirection4.N
import lt.frozenform.common.Input
import lt.frozenform.common.Matrix
import lt.frozenform.common.Point

fun main() {


    var loops = 0

    val matrix = Matrix.fromInput(Input(Main::class.java))
    val guardStart = matrix.find { it.value == '^' }!!.point()
    val (w, h) = matrix.size()

    val start = System.currentTimeMillis()

    for(x in 0 until w) {
        for(y in 0 until h) {
            if (matrix[x, y] == '#' || matrix[x, y] == '^') {
                continue
            }

            val visited = mutableSetOf<Point>()

            val m = matrix.copy()
            m[x, y] = '#'

            var location = guardStart

            visited.add(location)
//            println(location)

            var steps = 0

            var direction = N
            while(true) {
                var next = location + direction
                if(!m.contains(next)) {
                    println(steps)
                    break
                }
                while (m[next] == '#') {
                    direction = direction.cw()
                    next = location + direction
                }
                location = next

//                println(next)
                visited.add(next)

                steps++
                if (steps > 10000) {
//                    println(steps)
                    loops++
                    println(Point(x, y))
                    break
                }
            }
        }
    }

    val end = System.currentTimeMillis()
    println()
    println(loops)
    println("Duration: ${end - start} ms")

}