package com.example.hangman

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager

class HangmanStartFragment : Fragment(R.layout.activity_hangman_start) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        var sharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val startButton: Button = view.findViewById(R.id.btn_start)
        sharedPrefs.registerOnSharedPreferenceChangeListener { sharedPreferences, _ ->
            sharedPrefs = sharedPreferences
        }
        startButton.setOnClickListener {
            val direction = HangmanStartFragmentDirections.navigateToHangmanLevel(sharedPrefs.getInt(getString(R.string.pref_length_key), 5))
            findNavController().navigate(direction)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.activity_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                val direction = HangmanStartFragmentDirections.navigateToSettings()
                findNavController().navigate(direction)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}