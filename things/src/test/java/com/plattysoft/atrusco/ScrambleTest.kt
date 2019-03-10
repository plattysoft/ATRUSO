package com.plattysoft.atrusco

import junit.framework.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

/**
 * Created by Raul Portales on 10/03/2019.
 */
class ScrambleTest {

    @Test
    fun `A scramble should have the requested size`() {
        val scramble = Scramble()
        assertEquals(Scramble.DEFAULT_LENGTH, scramble.size)
    }

    @Test
    fun `A scramble should not have the same face twice on a row`() {
        val scramble = Scramble()
        for (i in 1..scramble.size-1) {
            assertNotEquals(scramble[i-1].face, scramble[i].face)
        }
    }

    @Test
    fun `toString should be ready to display on a screen`() {
        val scramble = Scramble()
        System.out.print(scramble.toString())
    }
}