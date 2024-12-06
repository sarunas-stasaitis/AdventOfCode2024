package lt.frozenform.common

enum class CardinalDirection4 {

    N(0, -1),
    E(1, 0),
    S(0, 1),
    W(-1, 0);

    val dx: Int
    val dy: Int

    constructor(dx: Int, dy: Int) {
        this.dx = dx
        this.dy = dy
    }

    fun cw() : CardinalDirection4 {
        return when(this) {
            N -> E
            E -> S
            S -> W
            W -> N
        }
    }

    fun ccw() : CardinalDirection4 {
        return when(this) {
            N -> W
            W -> S
            S -> E
            E -> N
        }
    }

}