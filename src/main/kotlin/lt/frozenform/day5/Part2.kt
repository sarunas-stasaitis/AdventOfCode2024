package lt.frozenform.day5

import lt.frozenform.common.Input
import org.jooq.lambda.Seq

fun main() {

    fun orderCorrect(order: List<Int>, rules: List<Pair<Int, Int>>): Boolean {
        for (rule in rules) {
            val i1 = order.indexOf(rule.first)
            val i2 = order.indexOf(rule.second)
            if (i1 == -1 || i2 == -1) {
                continue
            }
            if (i1 > i2) {
                return false
            }
        }

        return true
    }

    val rules = mutableListOf<Pair<Int, Int>>()
    val orders = mutableListOf<List<Int>>()

    var parsingRules = true
    Input(Main::class.java).forEach {
        if (it.isEmpty()) {
            parsingRules = false
        } else if (parsingRules) {
            val (min, max) = it.split("|").map { it.toInt() }
            rules.add(min to max)
        } else {
            orders.add(it.split(",").map { it.toInt() })
        }
    }

    val ruleMap: Map<Set<Int>, Pair<Int, Int>> = Seq.seq(rules).toMap { setOf(it.first, it.second) }

    var sum = 0

    val ruleComparator = Comparator<Int> { o1, o2 ->
        val rule: Pair<Int, Int>? = ruleMap[setOf<Int>(o1, o2)]
        if (rule == null) {
            0
        } else {
            if(rule.first == o1) {
                -1
            } else {
                1
            }
        }
    }

    for (order in orders) {
        if (!orderCorrect(order, rules)) {
            val sorted = order.sortedWith(ruleComparator)
            println("$order -> $sorted")
            sum += sorted[sorted.size / 2]
        }
    }

    println()
    println(sum)

}
