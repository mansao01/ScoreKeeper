package com.example.a201953120_firstproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.a201953120_firstproject.databinding.ActivityMainBinding

private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "Settings")

var score1 = 0
var score2 = 0

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = resources.getString(R.string.scorekeeper)

        binding.apply {
            btnMinusOne.setOnClickListener {
                score1--

                if (score1 <= 0) {
                    score1 = 0
                }
                binding.tvScoreTeam1.text = score1.toString()
            }

            btnPlusOne.setOnClickListener {
                score1++

                binding.tvScoreTeam1.text = score1.toString()
            }

            btnMinusTwo.setOnClickListener {
                score2--

                if (score2 <= 0) {
                    score2 = 0
                }
                binding.tvScoreTeam2.text = score2.toString()

            }

            btnPlusTwo.setOnClickListener {
                score2++

                binding.tvScoreTeam2.text = score2.toString()
            }
        }

        val pref = SettingsPreferences.getInstance(datastore)
        val settingsViewModel =
            ViewModelProvider(this, ViewModelFactory(pref))[SettingsViewModel::class.java]

        settingsViewModel.getThemeSettings().observe(this) { isDarkModeActivate: Boolean ->
            if (isDarkModeActivate) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                val moveToSettingsIntent = Intent(this, SettingsActivity::class.java)
                startActivity(moveToSettingsIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}