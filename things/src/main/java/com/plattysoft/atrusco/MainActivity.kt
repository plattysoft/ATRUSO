package com.plattysoft.atrusco

import android.app.Activity
import android.os.Bundle
import java.io.IOException
import android.util.Log
import com.plattysoft.pca9685.PCA9685
import com.plattysoft.pca9685.ServoUnderPca9685

private val TAG = MainActivity::class.java.simpleName

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupServo()
        try {
            claw.resetRotation()
            while (true) {
                claw.grab()
                Thread.sleep(1000)
                claw.turnClockWise()
                Thread.sleep(1000)
                claw.release()
                Thread.sleep(1000)
                claw.resetRotation()
                Thread.sleep(1000)
                claw.grab()
                Thread.sleep(2000)

            }
        } catch (e: IOException) {
            Log.e(TAG, "Error setting the angle", e)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        destroyServo()
    }

    private lateinit var pca9685: PCA9685

    private lateinit var claw: Claw

    private fun setupServo() {
        try {
            pca9685 = PCA9685()
            claw = Claw(pca9685, 0, 1)
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
