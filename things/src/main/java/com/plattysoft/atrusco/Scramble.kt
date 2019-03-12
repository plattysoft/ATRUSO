package com.plattysoft.atrusco

/**
 * Created by Raul Portales on 10/03/2019.
 */
class Scramble(numItems: Int = DEFAULT_LENGTH) : ArrayList<Movement>() {

    companion object {
        const val DEFAULT_LENGTH = 25
    }

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