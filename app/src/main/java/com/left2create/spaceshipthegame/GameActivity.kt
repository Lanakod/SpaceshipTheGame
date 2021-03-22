package com.left2create.spaceshipthegame

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.LinearLayout

class GameActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val ship = findViewById<ImageView>(R.id.imageView2)

        val sharedPreference =  getSharedPreferences(getString(R.string.shipPreference), Context.MODE_PRIVATE)
        ship.setImageResource(sharedPreference.getInt(getString(R.string.chosedShip), 0))

        findViewById<LinearLayout>(R.id.LinearLayout).setOnTouchListener { _, m ->
            if(m.action == MotionEvent.ACTION_MOVE)
            {
                ship.x = m.x - ship.width / 2
                ship.y = m.y - ship.height / 2
            }
            true
        }

//        val mainHandler = Handler(Looper.getMainLooper())
//        mainHandler.post(object : Runnable {
//            override fun run() {
//                mainHandler.postDelayed(this, 1000)
//            }
//        })
    }
}