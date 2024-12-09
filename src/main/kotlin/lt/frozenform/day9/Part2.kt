package lt.frozenform.day9

import lt.frozenform.common.Input

fun main() {

    val input = Input(Main::class.java).iterator().next()


    fun compact(input: IntArray) {
        var id = input.max()

        data class File(val start: Int, val end: Int) {
            fun size() = end - start
        }

        fun nextFile(): File {
            val start = input.indexOf(id)
            var end = start
            while (end < input.size && input[end] == id) {
                end++
            }
            id--
            return File(start, end)
        }

        fun findSpace(fileStart: Int, size: Int): Int? {
            var spaceStart = 0

            fun findSpanSize(): Int {
                var spanSize = 0
                while (input[spaceStart + spanSize] == -1) {
                    spanSize++
                }
                return spanSize
            }

            fun nextSpaceStart() {
                while (input[spaceStart] != -1) {
                    spaceStart++
                }
            }

            while (spaceStart < fileStart) {
                val spanSize = findSpanSize()
                if (spanSize >= size) {
                    return spaceStart
                } else {
                    spaceStart += spanSize
                }
                nextSpaceStart()
            }

            return null
        }

        while (id >= 0) {
            val file = nextFile()
            var space = findSpace(file.start, file.size())
            if (space != null) {
                for (i in file.start until file.end) {
                    input.swap(i, space++!!)
                }
            }
        }


    }


    val space = calcSpaceNeeded(input)
    println("Space needed: $space")

    val disk = expand(input)
    compact(disk)
    val cs = checksum(disk)
    println("Checksum: $cs")

}