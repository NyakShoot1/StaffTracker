package com.nyakshoot.stafftrackersimplenavigation.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nyakshoot.stafftrackersimplenavigation.data.api.ApiHelper

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (ViewModel::class.java.isAssignableFrom(modelClass)) {
            try {
                val constructor = modelClass.getConstructor(ApiHelper::class.java)
                return constructor.newInstance(apiHelper)
            } catch (e: Exception) {
                throw RuntimeException("Unable to create instance of $modelClass. Make sure it has a constructor that accepts ApiHelper and PreferenceManager.", e)
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}