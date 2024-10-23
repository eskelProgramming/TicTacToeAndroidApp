package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
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

        // Initialize the PlayerInfo text view
    }

    fun newGame(view: View) {
        for (button in buttons) {
            button.text = " " // Set the text to blank
            button.isEnabled = true // Ensure the button is enabled
        }


    }
}
