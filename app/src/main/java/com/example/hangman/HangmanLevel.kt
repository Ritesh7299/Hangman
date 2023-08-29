package com.example.hangman

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class HangmanLevel : Fragment(R.layout.activity_hangman_level) {

    private val viewModel: WordViewModel by viewModels()
    private val args: HangmanLevelArgs by navArgs()
    private var word: String = ""
    private var displayedWord: String = ""
    private var guessedLetters = 0
    private var guesses = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadWordResults(args.length)

        val textView = view.findViewById<TextView>(R.id.hangman_word)
        val aButton = view.findViewById<Button>(R.id.button_alphabet_a)
        val bButton = view.findViewById<Button>(R.id.button_alphabet_b)
        val cButton = view.findViewById<Button>(R.id.button_alphabet_c)
        val dButton = view.findViewById<Button>(R.id.button_alphabet_d)
        val eButton = view.findViewById<Button>(R.id.button_alphabet_e)
        val fButton = view.findViewById<Button>(R.id.button_alphabet_f)
        val gButton = view.findViewById<Button>(R.id.button_alphabet_g)
        val hButton = view.findViewById<Button>(R.id.button_alphabet_h)
        val iButton = view.findViewById<Button>(R.id.button_alphabet_i)
        val jButton = view.findViewById<Button>(R.id.button_alphabet_j)
        val kButton = view.findViewById<Button>(R.id.button_alphabet_k)
        val lButton = view.findViewById<Button>(R.id.button_alphabet_l)
        val mButton = view.findViewById<Button>(R.id.button_alphabet_m)
        val nButton = view.findViewById<Button>(R.id.button_alphabet_n)
        val oButton = view.findViewById<Button>(R.id.button_alphabet_o)
        val pButton = view.findViewById<Button>(R.id.button_alphabet_p)
        val qButton = view.findViewById<Button>(R.id.button_alphabet_q)
        val rButton = view.findViewById<Button>(R.id.button_alphabet_r)
        val sButton = view.findViewById<Button>(R.id.button_alphabet_s)
        val tButton = view.findViewById<Button>(R.id.button_alphabet_t)
        val uButton = view.findViewById<Button>(R.id.button_alphabet_u)
        val vButton = view.findViewById<Button>(R.id.button_alphabet_v)
        val wButton = view.findViewById<Button>(R.id.button_alphabet_w)
        val xButton = view.findViewById<Button>(R.id.button_alphabet_x)
        val yButton = view.findViewById<Button>(R.id.button_alphabet_y)
        val zButton = view.findViewById<Button>(R.id.button_alphabet_z)

        val backButton = view.findViewById<Button>(R.id.button_back)
        backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        fun handleLetterButton(button: Button, letter: Char, textView: TextView, view: View) {
            button.isEnabled = false
            Log.d("HangmanLevel", "Triggering")
            var ind = 0
            val currentDisplayedWord = displayedWord
            displayedWord = ""
            word.forEach {
                if (it == letter) {
                    displayedWord += it
                    guessedLetters += 1
                } else {
                    displayedWord += currentDisplayedWord[2 * ind]
                }
                if (ind != word.length - 1) {
                    displayedWord += " "
                }
                ind += 1
            }
            guesses += 1
            if (guessedLetters >= args.length) {
                endLevel(view)
            }
            Log.d("HangmanLevel", displayedWord)
            textView.text = displayedWord
        }

        viewModel.searchResults.observe(viewLifecycleOwner) { searchResults ->
            Log.d("HangmanLevel", searchResults.toString())
            if (!searchResults.isNullOrEmpty()) {
                word = searchResults[0]
            }
            if (word.isNotEmpty()) {
                displayedWord = "_ ".repeat(word!!.length - 1) + "_"
            }
            textView.text = displayedWord

            // create a map to store the button views with their corresponding letters as keys
            val letterButtons = mapOf(
                'a' to aButton,
                'b' to bButton,
                'c' to cButton,
                'd' to dButton,
                'e' to eButton,
                'f' to fButton,
                'g' to gButton,
                'h' to hButton,
                'i' to iButton,
                'j' to jButton,
                'k' to kButton,
                'l' to lButton,
                'm' to mButton,
                'n' to nButton,
                'o' to oButton,
                'p' to pButton,
                'q' to qButton,
                'r' to rButton,
                's' to sButton,
                't' to tButton,
                'u' to uButton,
                'v' to vButton,
                'w' to wButton,
                'x' to xButton,
                'y' to yButton,
                'z' to zButton
            )
            // set the OnClickListener for each button in the map
            for ((letter, button) in letterButtons) {
                button.setOnClickListener { handleLetterButton(button, letter, textView, view) }
            }

        }
    }

    private fun endLevel(view: View) {
        val scoreView = view.findViewById<TextView>(R.id.hangman_score)
        val letterButtons = listOf(
            view.findViewById<Button>(R.id.button_alphabet_a),
            view.findViewById<Button>(R.id.button_alphabet_b),
            view.findViewById<Button>(R.id.button_alphabet_c),
            view.findViewById<Button>(R.id.button_alphabet_d),
            view.findViewById<Button>(R.id.button_alphabet_e),
            view.findViewById<Button>(R.id.button_alphabet_f),
            view.findViewById<Button>(R.id.button_alphabet_g),
            view.findViewById<Button>(R.id.button_alphabet_h),
            view.findViewById<Button>(R.id.button_alphabet_i),
            view.findViewById<Button>(R.id.button_alphabet_j),
            view.findViewById<Button>(R.id.button_alphabet_k),
            view.findViewById<Button>(R.id.button_alphabet_l),
            view.findViewById<Button>(R.id.button_alphabet_m),
            view.findViewById<Button>(R.id.button_alphabet_n),
            view.findViewById<Button>(R.id.button_alphabet_o),
            view.findViewById<Button>(R.id.button_alphabet_p),
            view.findViewById<Button>(R.id.button_alphabet_q),
            view.findViewById<Button>(R.id.button_alphabet_r),
            view.findViewById<Button>(R.id.button_alphabet_s),
            view.findViewById<Button>(R.id.button_alphabet_t),
            view.findViewById<Button>(R.id.button_alphabet_u),
            view.findViewById<Button>(R.id.button_alphabet_v),
            view.findViewById<Button>(R.id.button_alphabet_w),
            view.findViewById<Button>(R.id.button_alphabet_x),
            view.findViewById<Button>(R.id.button_alphabet_y),
            view.findViewById<Button>(R.id.button_alphabet_z)
        )
        for (button in letterButtons) {
            button.isVisible = false
        }
        scoreView.text = "Congratulations! You got the answer in $guesses guesses!"
    }
}