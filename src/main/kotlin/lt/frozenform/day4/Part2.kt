package lt.frozenform.day4

import lt.frozenform.common.Ext.Companion.string
import lt.frozenform.common.Input
import lt.frozenform.common.Matrix
import org.jooq.lambda.Seq
import java.util.*

fun main() {

    val matrix = Matrix.fromInput(Input(Main::class.java))

    var count = 0

    val xmas = "XMAS".toRegex()

    for (x in 0 until matrix.width - 2) {
        for (y in 0 until matrix.height - 2) {
            val submatrix = matrix.submatrix(x, y, 3, 3)
            val lr = submatrix.diagLR(2).string()
            val rl = submatrix.diagRL(2).string()
            if ((lr == "MAS" || lr == "SAM") && (rl == "MAS" || rl == "SAM")) {
                count++
            }
        }
    }

    println(count)
}
