package lt.frozenform.day8

import lt.frozenform.common.Input
import lt.frozenform.common.Matrix
import lt.frozenform.common.Point
import org.jooq.lambda.Seq

fun main() {

    val m = Matrix.fromInput(Input(Main::class.java))

    val frequencies = Seq.seq(m)
        .filter { it.value != '.' }
        .map { it.value }
        .toSet()

    val frequencyNodes = mutableMapOf<Char, List<Point>>()
    for (frequency in frequencies) {
        val nodes = Seq.seq(m)
            .filter { it.value == frequency }
            .map { it.point() }
            .toList()
        frequencyNodes[frequency] = nodes
    }

    val allNodes = mutableSetOf<Point>()
    for ((frequency, nodes) in frequencyNodes) {
        val frequencyNodes = Seq.crossJoin(nodes, nodes)
            .filter { it.v1 != it.v2 }
            .flatMap {
                val delta = it.v2 - it.v1
                val points = mutableListOf<Point>()

                var pt = it.v2
                while (m.contains(pt)) {
                    points.add(pt)
                    pt += delta
                }
                while (m.contains(pt)) {
                    points.add(pt)
                    pt -= delta
                }

                points.stream()
            }
            .filter(m::contains)
            .toList()
        allNodes.addAll(frequencyNodes)
    }

    Seq.seq(allNodes)
        .forEach { m[it] = '#' }

    m.print(System.out)

    println(allNodes.size)


}

