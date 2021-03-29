package com.left2create.spaceshipthegame

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)
        val image = findViewById<ImageView>(R.id.imageView)

        val sharedPreference =  getSharedPreferences(getString(R.string.shipPreference), Context.MODE_PRIVATE)
        if (sharedPreference.getInt(getString(R.string.chosedShip), 0) == 0) {
            val editor = sharedPreference.edit()
            editor.putInt(getString(R.string.chosedShip), R.drawable.spaceship_orange)
            editor.apply()
        }
        image.setImageResource(sharedPreference.getInt(getString(R.string.chosedShip), 0))
        image.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fadein))

        Handler().postDelayed(Runnable {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)

    }
}