package com.plattysoft.atrusco

import android.app.Activity
import android.os.Bundle
import java.io.IOException
import android.util.Log
import android.view.View
import com.plattysoft.pca9685.PCA9685

private val TAG = MainActivity::class.java.simpleName

class MainActivity : Activity() {

    lateinit var cubeManipulator: CubeManipulator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cubeManipulator = CubeManipulator()

        setContentView(R.layout.main_activity)

        val scramble = Scramble()
        Log.d("Scramble", scramble.toString())

        findViewById<View>(R.id.grab_cube).setOnClickListener {
            cubeManipulator.grabCarefully()
        }

        findViewById<View>(R.id.twist_face).setOnClickListener {
            cubeManipulator.twistFace()
        }

        findViewById<View>(R.id.turn_cube).setOnClickListener {
            cubeManipulator.turnCube()
        }

        findViewById<View>(R.id.reset).setOnClickListener {
            cubeManipulator.resetState()
        }

        findViewById<View>(R.id.test).setOnClickListener {
            cubeManipulator.test()
        }
    }

    fun performSequence(moves: List<Movement>) {
        for (move in moves) {
            cubeManipulator.performMove(move)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cubeManipulator.close()
    }

}
