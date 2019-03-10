package com.plattysoft.atrusco

import android.app.Activity
import android.os.Bundle
import java.io.IOException
import android.util.Log
import android.view.View
import com.plattysoft.pca9685.PCA9685

private val TAG = MainActivity::class.java.simpleName

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupServo()

        setContentView(R.layout.main_activity)

        val scramble = Scramble()
        Log.d("Scramble", scramble.toString())

        findViewById<View>(R.id.grab_cube).setOnClickListener {
            grabCarefully()
        }

        findViewById<View>(R.id.twist_face).setOnClickListener {
            twistFace()
        }

        findViewById<View>(R.id.turn_cube).setOnClickListener {
            turnCube()
        }

        findViewById<View>(R.id.reset).setOnClickListener {
            resetState()
        }

        findViewById<View>(R.id.test).setOnClickListener {
            test()
        }
    }

    private fun test() {
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

    private fun turnCube() {
        rightClaw.grab()
        leftClaw.release()
        rightClaw.turnClockWise()
        Thread.sleep(600)
        leftClaw.grab()
        Thread.sleep(400)
        rightClaw.release()
        Thread.sleep(400)
        rightClaw.resetRotation()
        Thread.sleep(600)
        rightClaw.grab()
    }

    private fun resetState() {
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

    fun twistFace() {
        leftClaw.grab()
        Thread.sleep(200)
        rightClaw.turnClockWise()
        Thread.sleep(600)
        rightClaw.release()
        Thread.sleep(800)
        rightClaw.resetRotation()
        Thread.sleep(600)
        rightClaw.grab()
    }

    override fun onDestroy() {
        super.onDestroy()
        destroyServo()
    }

    private lateinit var pca9685: PCA9685

    private lateinit var rightClaw: Claw
    private lateinit var leftClaw: Claw

    private fun setupServo() {
        try {
            pca9685 = PCA9685()
            rightClaw = Claw(pca9685, 0, 1)
            rightClaw.setGripPulseDurationRange(1.0, 2.5)
            leftClaw = Claw(pca9685, 2, 3)
            leftClaw.setGripPulseDurationRange(1.0, 2.0)
        } catch (e: IOException) {
            Log.e(TAG, "Error creating Servo", e)
        }

    }

    private fun destroyServo() {
        try {
            pca9685.close()
        } catch (e: IOException) {
            Log.e(TAG, "Error closing Servo")
        }
    }

}
