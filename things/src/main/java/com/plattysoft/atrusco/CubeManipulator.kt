package com.plattysoft.atrusco

import android.util.Log
import com.plattysoft.pca9685.PCA9685
import java.io.IOException
import java.lang.UnsupportedOperationException

/**
 * Created by Raul Portales on 11/03/2019.
 */
class CubeManipulator: AutoCloseable {

    private val pca9685 = PCA9685()

    private val rightClaw: Claw
    private val leftClaw: Claw

    init {
        rightClaw = Claw(pca9685, 0, 1)
        rightClaw.setGripPulseDurationRange(1.0, 2.5)
        leftClaw = Claw(pca9685, 2, 3)
        leftClaw.setGripPulseDurationRange(1.0, 2.0)
    }

    override fun close() {
        pca9685.close()
    }

    fun test() {
        Thread.sleep(600)
        leftClaw.turnClockWise()
        Thread.sleep(600)
        leftClaw.turnCounterClockWise()
        Thread.sleep(600)
        leftClaw.resetRotation()
//        rightClaw.grab()
//        leftClaw.grab()
//        Thread.sleep(600)
//        rightClaw.release()
//        Thread.sleep(600)
//        rightClaw.grab()
//        Thread.sleep(600)
//        leftClaw.release()
//        Thread.sleep(600)
//        leftClaw.grab()
//        Thread.sleep(600)
    }

    fun turnCube(direction: Direction) {
        turnCube(direction, rightClaw, leftClaw)
    }

    fun turnCube(direction: Direction, turnClaw: Claw, otherClaw: Claw) {
        turnClaw.grab()
        otherClaw.release()
        turnClaw.turn(direction)
        Thread.sleep(600)
        otherClaw.grab()
        Thread.sleep(400)
        turnClaw.release()
        Thread.sleep(400)
        turnClaw.resetRotation()
        Thread.sleep(600)
        turnClaw.grab()
    }

    fun resetState() {
        rightClaw.resetRotation()
        leftClaw.resetRotation()
        rightClaw.release()
        leftClaw.release()
    }


    fun grabCarefully() {
        rightClaw.resetRotation()
        leftClaw.resetRotation()
        rightClaw.release()
        leftClaw.release()
        Thread.sleep(600)
        rightClaw.grabSoftly()
        leftClaw.grabSoftly()
    }

    private fun twistU(direction: Direction) {
        leftClaw.grab()
        rightClaw.grab()
        Thread.sleep(200)
        rightClaw.turn(direction)
        Thread.sleep(600)
        rightClaw.release()
        Thread.sleep(800)
        rightClaw.resetRotation()
        Thread.sleep(600)
        rightClaw.grab()
    }

    private fun twistR(direction: Direction) {
        leftClaw.grab()
        rightClaw.grab()
        Thread.sleep(200)
        leftClaw.turn(direction)
        Thread.sleep(600)
        leftClaw.release()
        Thread.sleep(800)
        leftClaw.resetRotation()
        Thread.sleep(600)
        leftClaw.grab()
    }

    private fun twistB(direction: Direction) {
        turnCube(Direction.CW)
        // Now, turn
        twistR(direction)
        // Undo the turn cube
        turnCube(Direction.CCW)
    }

    private fun twistF(direction: Direction) {
        turnCube(Direction.CCW)
        // Now, turn
        twistR(direction)
        // Undo the turn cube
        turnCube(Direction.CW)
    }

    private fun twistL(direction: Direction) {
        turnCube(Direction.CW, leftClaw, rightClaw)
        turnCube(Direction.CW, leftClaw, rightClaw)
        // Now, turn
        twistU(direction)
        // Undo the turn cube
        turnCube(Direction.CCW, leftClaw, rightClaw)
        turnCube(Direction.CCW, leftClaw, rightClaw)
    }

    private fun twistD(direction: Direction) {
        turnCube(Direction.CCW)
        // Now, turn
        twistR(direction)
        // Undo the turn cube
        turnCube(Direction.CW)
    }

    fun performMove(move: Movement) {
        // Add it to the list of movements to do
        // Once a movement is completed, we take the next
        // Direction half turn should just be clockwise twice
        when (move.face) {
            // The simple ones
            Face.R -> twistR(move.direction)
            Face.U -> twistU(move.direction)
            // The slightly slower ones
            Face.B -> twistB(move.direction)
            Face.F -> twistF(move.direction)
            // The very slow ones, should try to minimize those
            Face.L -> twistL(move.direction)
            Face.D -> twistD(move.direction)
        }
    }

}