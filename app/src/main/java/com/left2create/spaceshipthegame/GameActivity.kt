package com.left2create.spaceshipthegame

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random


class GameActivity : AppCompatActivity() {

    val enemies : MutableList<ImageView> = ArrayList()
    val speed : Array<Float> = arrayOf(0f,0f,0f,0f,0f)
    var enemyID = 10000

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val ship = findViewById<ImageView>(R.id.imageView2)
        val layout = findViewById<LinearLayout>(R.id.LinearLayout)

        val sharedPreference =  getSharedPreferences(getString(R.string.shipPreference), Context.MODE_PRIVATE)
        ship.setImageResource(sharedPreference.getInt(getString(R.string.chosedShip), 0))

        layout.setOnTouchListener { _, m ->

            if(m.action == MotionEvent.ACTION_MOVE && isInBox(m.x, m.y, ship.x, ship.y, ship.x + ship.width, ship.y + ship.height))
            {
                ship.x = m.x - ship.width / 2
                ship.y = m.y - ship.height / 2
            }
            true
        }

        for (i in 0 until 5)
        {
            val enemy = ImageView(this)
            enemy.setImageResource(R.drawable.meteor)
            enemy.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            enemy.id = enemyID++
            enemy.x = Random.nextInt(-500, 500).toFloat()
            enemy.y = -2500f
            enemies.add(i, enemy)
            speed[i] = Random.nextInt(1, 15).toFloat()
            layout.addView(enemy)
        }

        object : CountDownTimer(60000, 10) {
            override fun onTick(millisUntilFinished: Long) {
                for (i in 0 until 5)
                {
                    enemies[i].y += speed[i]
                    if(enemies[i].y >= 2000) recreateEnemy(i)
                    if(isInBox(enemies[i].x + enemies[i].width / 2, enemies[i].y + enemies[i].height / 2, ship.x, ship.y, ship.x + ship.width, ship.y + ship.height))
                    {
                        finish()
                    }
                }
            }

            override fun onFinish() {

            }
        }.start()

    }

    private fun recreateEnemy(i : Int)
    {
        enemies[i].x = Random.nextInt(-500, 500).toFloat()
        enemies[i].y = -2500f
        speed[i] = Random.nextInt(3, 15).toFloat()
    }

    private fun isInBox(x: Float, y: Float, x1: Float, y1: Float, x2: Float, y2: Float) : Boolean
    {
        if(x in x1..x2 && y in y1..y2) return true
        return false
    }
}