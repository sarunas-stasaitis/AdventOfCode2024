package lt.frozenform.day7

enum class Operator1 {

    ADD {
        override fun invoke(a: Long, b: Long): Long {
            return a + b
        }
    },
    MULTIPLY {
        override fun invoke(a: Long, b: Long): Long {
            return a * b
        }
    };

    abstract operator fun invoke(a: Long, b: Long): Long

}