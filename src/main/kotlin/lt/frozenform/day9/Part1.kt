package lt.frozenform.day9

import lt.frozenform.common.Input

fun main() {

    val input = Input(Main::class.java).iterator().next()


    fun compact(input: IntArray) {
        var dataPointer = input.size - 1
        var spacePointer = 0

        fun updateSpacePointer() {
            while(spacePointer < input.size && input[spacePointer] != -1) {
                spacePointer++
            }
        }

        fun updateDataPointer() {
            while(dataPointer >= 0 && input[dataPointer] == -1) {
                dataPointer--
            }
        }

        updateSpacePointer()
        updateDataPointer()

        while (dataPointer > spacePointer) {
            input.swap(spacePointer, dataPointer)
            updateSpacePointer()
            updateDataPointer()
        }


    }


    val space = calcSpaceNeeded(input)
    println("Space needed: $space")

    val disk = expand(input)
    compact(disk)
    val cs = checksum(disk)
    println("Checksum: $cs")

}

fun expand(input: String): IntArray {
    val result = IntArray(calcSpaceNeeded(input).toInt())

    var id = 0
    var index = 0
    var file = true
    for(c in input.toCharArray()) {
        val n = c.digitToInt()
        if(file) {
            for(i in 0 until n) {
                result[index++] = id
            }
            id++
        } else {
            for(i in 0 until n) {
                result[index++] = -1
            }
        }
        file = !file
    }

    return result
}

fun IntArray.swap(a: Int, b: Int) {
    val temp = this[a]
    this[a] = this[b]
    this[b] = temp
}

fun checksum(input: IntArray): Long {
    var cheksum = 0L

    for(i in input.indices) {
        if(input[i] != -1) {
            cheksum += i * input[i]
        }
    }
    return cheksum
}

fun calcSpaceNeeded(input: String): Long {
    var size = 0L

    input.forEach {
        size += it.digitToInt()
    }
    return size
}