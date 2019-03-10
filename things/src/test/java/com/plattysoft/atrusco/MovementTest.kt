package com.plattysoft.atrusco

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Test

/**
 * Created by Raul Portales on 10/03/2019.
 */
class MovementTest {

    @Test
    fun testStringExport() {
        assertEquals("R", Movement(Face.R, Direction.CW).toString())
        assertEquals("U2", Movement(Face.U, Direction.HALF).toString())
        assertEquals("B'", Movement(Face.B, Direction.CCW).toString())
        assertEquals("L", Movement(Face.L, Direction.CW).toString())
        assertEquals("D2", Movement(Face.D, Direction.HALF).toString())
        assertEquals("F", Movement(Face.F, Direction.CW).toString())
    }

    @Test
    fun `getRandom should return values accross the faces and directions covering all the options`() {
        // GIVEN we generate 100 movements at random
        val list = mutableListOf<Movement>()
        for (i in 0..100) {
            list.add(Movement.getRandom())
        }
        // THEN we should have all faces
        val facesSeen = HashSet<Face>()
        // AND we should have all directions
        val directionsSeen = HashSet<Direction>()
        for (movement in list) {
            facesSeen.add(movement.face)
            directionsSeen.add(movement.direction)
        }
        for (face in Face.values()) {
            assertTrue(facesSeen.contains(face))
        }
        for (direction in Direction.values()) {
            assertTrue(directionsSeen.contains(direction))
        }
    }
}