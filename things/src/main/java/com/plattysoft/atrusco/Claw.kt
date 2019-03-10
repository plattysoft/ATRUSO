package com.plattysoft.atrusco

import com.plattysoft.pca9685.PCA9685
import com.plattysoft.pca9685.ServoUnderPca9685

/**
 * Created by Raul Portales on 01/11/2018.
 */
class Claw(pca9685: PCA9685, gripChannel: Int, rotationChannel: Int) {

    private val gripServo: ServoUnderPca9685
    private val rotationServo: ServoUnderPca9685

    init {
        gripServo = pca9685.openServo(gripChannel)
        gripServo.setPulseDurationRange(1.0, 2.4)
        gripServo.setAngleRange(0.0, 100.0)
        gripServo.setEnabled(true)

        rotationServo = pca9685.openServo(rotationChannel)
        rotationServo.setPulseDurationRange(.6, 2.75)
        rotationServo.setAngleRange(-90.0, 90.0)
        rotationServo.setEnabled(true)
    }

    fun grab() {
        gripServo.angle = 60.0
    }

    fun release() {
        gripServo.angle = 0.0
    }

    fun turnClockWise() {
        rotationServo.angle = 90.0
    }

    fun turnCounterClockWise() {
        rotationServo.angle = -90.0
    }

    fun resetRotation() {
        rotationServo.angle = 0.0
    }

    fun grabSoftly() {
        gripServo.angle = 35.0
    }

    fun setGripPulseDurationRange(minPulse: Double, maxPulse: Double) {
        gripServo.setPulseDurationRange(minPulse, maxPulse)
    }
}