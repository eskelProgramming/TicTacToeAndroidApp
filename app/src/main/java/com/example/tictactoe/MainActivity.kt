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

    // A 2 dimensional list so that it's easier to find wins
    private lateinit var buttons: List<List<Button>>

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

        // Made as a two dimensional array so that it's
        // easier to find wins
        buttons = listOf(
            listOf(btnTopLeft, btnTopMid, btnTopRight),
            listOf(btnMiddleLeft, btnMiddle, btnMiddleRight),
            listOf(btnBottomLeft, btnBottomMiddle, btnBottomRight)
        )

        // Set click listeners for each button
        for (list in buttons) {
            for (button in list) {
                button.setOnClickListener { view -> onPlayButtonClick(view) }
            }
        }

        // Initialize the PlayerInfo text view
        tvPlayerInfo = findViewById(R.id.tvPlayerInfo)

        // disable all buttons
        disablePlayButtons()
    }

    fun newGame(view: View) {
       tvPlayerInfo.text = getString(R.string.player_x_s_turn)

        for (list in buttons) {
            for (button in list) {
                button.text = " " // Set the text to blank
                button.isEnabled = true // Ensure the button is enabled
            }
        }
        // Make it player x's turn
        xTurn = true
    }

    private fun onPlayButtonClick(view: View) {
        val button = view as Button

        // disable the button so the user can no longer select it
        button.isEnabled = false

        // change the text of the button based on who's turn it is
        if (xTurn) {
            button.text = "X"
            tvPlayerInfo.text = getString(R.string.player_O_s_turn)
        }
        else {
            tvPlayerInfo.text = getString(R.string.player_x_s_turn)
            button.text = "O"
        }

        // check for a win
        isWin()

        // check for a tie
        isTie()

        // set xTurn to the opposite of what it currently is
        xTurn = !xTurn
    }

    // A method to check if there is a win on the board.
    // If there is, it will display the results,
    // and lock the play buttons
    private fun isWin(){
        if (isHorizontalWin() || isVerticalWin() || isDiagonalWin()) {
            tvPlayerInfo.text = if (xTurn) "Player X wins!" else "Player O Wins!"
            disablePlayButtons()
        }
    }

    // Checks if there is a win in a horizontal row
    private fun isHorizontalWin() : Boolean {
        for (i in 0..2) {
            // returns true if a row contains a win (all text values are the same)
            if (buttons[i][0].text.equals(buttons[i][1].text)
                && buttons[i][2].text.equals(buttons[i][0].text)
                && !buttons[i][0].text.isNullOrBlank()) return true
        }
        // returns false if no row contains a win
        return false
    }

    // Checks for a win in vertical columns
    private fun isVerticalWin() : Boolean {
        for (i in 0..2) {
            // returns true if there is a win in the columns
            if(buttons[0][i].text.equals(buttons[1][i].text)
                && buttons[2][i].text.equals(buttons[0][i].text)
                && !buttons[0][i].text.isNullOrBlank()) return true
        }
        // returns false if no column contains a win
        return false
    }

    // checks each diagonal for a win
    private fun isDiagonalWin(): Boolean {
        // returns true if there is a win from the top left
        // to the bottom right
        return if (btnTopLeft.text.equals(btnMiddle.text)
            && btnMiddle.text.equals(btnBottomRight.text)
            && !btnMiddle.text.isNullOrBlank()) true
        // returns true if there is a win from the top right
        // to the bottom left
        else if (btnTopRight.text.equals(btnMiddle.text)
            && btnMiddle.text.equals(btnBottomLeft.text)
            && !btnMiddle.text.isNullOrBlank()) true
        // otherwise returns false
        else false
    }

    // Checks for a tie, and if there is a tie
    // displays the tie message
    private fun isTie(){
        // checks for button that hasn't been clicked on yet
        for (list in buttons) {
            for (button in list) {
                // if a button hasn't been clicked on, return out of the loop
                if (button.text.isNullOrBlank()) return
            }
        }

        tvPlayerInfo.text = "It's a tie!"
        disablePlayButtons()
    }

    // disables all 9 play buttons on the screen
    private fun disablePlayButtons() {
        for (list in buttons) {
            for (button in list) {
                button.isEnabled = false
            }
        }
    }
}
