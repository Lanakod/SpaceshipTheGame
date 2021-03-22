package com.left2create.spaceshipthegame

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreference =  getSharedPreferences(getString(R.string.shipPreference), Context.MODE_PRIVATE)
        if(sharedPreference.getInt(getString(R.string.chosedShip), 0) == 0)
        {
            val editor = sharedPreference.edit()
            editor.putInt(getString(R.string.chosedShip), R.drawable.spaceship_orange)
            editor.apply()
        }
    }

    fun newGame(view: View) {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    fun chooseShip(view: View) {
        val intent = Intent(this, ShipChoose::class.java)
        startActivity(intent)
    }

    fun exitClick(view: View) {
        finish()
    }
}