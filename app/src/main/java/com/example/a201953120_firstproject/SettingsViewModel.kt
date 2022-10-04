package com.example.a201953120_firstproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SettingsViewModel(private val pref: SettingsPreferences): ViewModel() {
    fun getThemeSettings(): LiveData<Boolean> {
        return  pref.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDakModeActive: Boolean){
        viewModelScope.launch {
            pref.saveThemeSetting(isDakModeActive)
        }
    }
}