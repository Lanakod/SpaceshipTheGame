package com.left2create.spaceshipthegame

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class ShipChoose : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ship_choose)

        Toast.makeText(this, "Выберите корабль", Toast.LENGTH_LONG).show()
    }

    fun changeShip(view: View) {
        when(view.id)
        {
            R.id.blueShip -> saveShip(R.drawable.spaceship_blue)
            R.id.darkblueShip -> saveShip(R.drawable.spaceship_darkblue)
            R.id.greenShip -> saveShip(R.drawable.spaceship_green)
            R.id.orangeShip -> saveShip(R.drawable.spaceship_orange)
            R.id.purpleShip -> saveShip(R.drawable.spaceship_purple)
            R.id.redShip -> saveShip(R.drawable.spaceship_red)
            R.id.yellowShip -> saveShip(R.drawable.spaceship_yellow)
        }
    }

    private fun saveShip(imageID: Int)
    {
        val sharedPreference = getSharedPreferences(getString(R.string.shipPreference), Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putInt(getString(R.string.chosedShip), imageID)
        editor.apply()
        finish()
    }
}