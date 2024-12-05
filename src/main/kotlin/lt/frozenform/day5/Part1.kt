package lt.frozenform.day5

import lt.frozenform.common.Input

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

    var sum = 0

    for (order in orders) {
        if (orderCorrect(order, rules)) {
            sum += order[order.size / 2]
        }
    }

    println(sum)

}