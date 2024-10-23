package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // Initialize as properties so that they are visible outside of onCreate
    private lateinit var btnTopLeft: Button
    private lateinit var btnTopMid: Button
    private lateinit var btnTopRight: Button
    private lateinit var btnMiddleLeft: Button
    private lateinit var btnMiddle: Button
    private lateinit var btnMiddleRight: Button
    private lateinit var btnBottomLeft: Button
    private lateinit var btnBottomMiddle: Button
    private lateinit var btnBottomRight: Button

    private lateinit var buttons: List<Button>

    private lateinit var tvPlayerInfo: TextView

    // True if it's player X's turn, false if O's
    private var xTurn: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize the buttons
        btnTopLeft = findViewById(R.id.btnTopLeft)
        btnTopMid = findViewById(R.id.btnTopMiddle)
        btnTopRight = findViewById(R.id.btnTopRight)
        btnMiddleLeft = findViewById(R.id.btnMiddleLeft)
        btnMiddle = findViewById(R.id.btnMiddle)
        btnMiddleRight = findViewById(R.id.btnMiddleRight)
        btnBottomLeft = findViewById(R.id.btnBottomLeft)
        btnBottomMiddle = findViewById(R.id.btnBottomMiddle)
        btnBottomRight = findViewById(R.id.btnBottomRight)

        buttons = listOf(
            btnTopLeft, btnTopMid, btnTopRight, btnMiddleLeft, btnMiddle,
            btnMiddleRight, btnBottomLeft, btnBottomMiddle, btnBottomRight
        )

        // Set click listeners for each button
        for (button in buttons) {
            button.setOnClickListener { view -> onPlayButtonClick(view) }
        }

        // Initialize the PlayerInfo text view
        tvPlayerInfo = findViewById(R.id.tvPlayerInfo)
    }

    fun newGame(view: View) {
       tvPlayerInfo.text = getString(R.string.player_x_s_turn)

        for (button in buttons) {
            button.text = " " // Set the text to blank
            button.isEnabled = true // Ensure the button is enabled
        }

        xTurn = true
    }

    private fun onPlayButtonClick(view: View) {
        val button = view as Button
        if (xTurn) {
            button.text = "X"
            tvPlayerInfo.text = getString(R.string.player_O_s_turn)
        }
        else {
            tvPlayerInfo.text = getString(R.string.player_x_s_turn)
            button.text = "O"
        }
        // set xTurn to the opposite of what it currently is
        xTurn = !xTurn

        // disable the button so the user can no longer select it
        button.isEnabled = false
    }
}
