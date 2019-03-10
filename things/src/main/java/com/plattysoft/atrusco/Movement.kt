package com.plattysoft.atrusco

import java.util.*

data class Movement (val face: Face, val direction: Direction) {

    companion object {

        val random = Random()

        fun getRandom(): Movement {
            return Movement(getRandom(Face.values()),
                    getRandom(Direction.values()))
        }

        fun <T> getRandom(values: Array<T>) : T {
            return values[random.nextInt(values.size)]
        }
    }

    override fun toString(): String {
        return "${face}${direction}"
    }
}

enum class Face {
    U, D, R, L, F, B
}

enum class Direction {
    CW, CCW, HALF;

    override fun toString(): String {
        return when(this) {
            CW -> ""
            CCW -> "'"
            HALF -> "2"
        }
    }
}
