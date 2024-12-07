package lt.frozenform.day7

enum class Operator2 {

    ADD {
        override fun invoke(a: Long, b: Long): Long {
            return a + b
        }
    },
    MULTIPLY {
        override fun invoke(a: Long, b: Long): Long {
            return a * b
        }
    },
    CONCAT {
        override fun invoke(a: Long, b: Long): Long {
            return "$a$b".toLong()
        }
    };

    abstract operator fun invoke(a: Long, b: Long): Long

}