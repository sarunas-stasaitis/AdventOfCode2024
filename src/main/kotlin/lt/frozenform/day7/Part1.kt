package lt.frozenform.day7

import lt.frozenform.common.Input
import org.jooq.lambda.Seq

fun main() {

    val equations = Input(Main::class.java).map {
        val split1 = it.split(": ")
        val result = split1[0].toLong()
        val numbers = Seq.seq(split1[1].split(" "))
            .mapToLong(String::toLong)
            .toArray()
        Equation(result, numbers)
    }

    var total = 0L

    for (eq in equations) {
        val result = checkStep1(eq, eq.numbers[0], 1)
        println("$eq -> $result")
        if (result) {
            total += eq.result
        }
    }

    println()
    println(total)
}

fun checkStep1(eq: Equation, curentValue: Long, index: Int) : Boolean {
    for(op in Operator1.entries) {
        val updatedValue = op(curentValue, eq.numbers[index])
        if (updatedValue > eq.result) {
            continue
        }
        if (updatedValue == eq.result && index == eq.numbers.size - 1) {
            return true
        }
        if (index + 1 >= eq.numbers.size) {
            continue
        }
        if (checkStep1(eq, updatedValue, index + 1)) {
            return true
        }
    }
    return false
}

