package com.plattysoft.atrusco

/**
 * Created by Raul Portales on 10/03/2019.
 */
class Scramble: ArrayList<Movement>() {

    companion object {
        const val DEFAULT_LENGTH = 25
    }

    class Builder {
        fun build(): Scramble{
            return Scramble()
        }
    }

    val numItems = DEFAULT_LENGTH

    init {
        val movement = Movement.getRandom()
        add(movement)
        while (size < numItems) {
            val movement = Movement.getRandom()
            // On a different face than the last one
            if (get(size-1).face != movement.face) {
                add(movement)
            }
        }
    }
}