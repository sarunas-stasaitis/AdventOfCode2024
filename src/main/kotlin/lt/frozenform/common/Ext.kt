package lt.frozenform.common

class Ext {

    companion object {
        fun Array<Char>.string(): String {
            return this.joinToString("")
        }
    }

}