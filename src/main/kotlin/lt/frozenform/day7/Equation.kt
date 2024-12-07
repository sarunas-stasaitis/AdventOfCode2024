package lt.frozenform.day7

class Equation(
    val result: Long,
    val numbers: LongArray
) {
    override fun toString(): String {
        return "$result: ${numbers.joinToString(" ")}"
    }
}