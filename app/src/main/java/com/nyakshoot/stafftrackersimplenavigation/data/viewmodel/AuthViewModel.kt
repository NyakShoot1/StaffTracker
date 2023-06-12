package com.nyakshoot.stafftrackersimplenavigation.data.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyakshoot.stafftrackersimplenavigation.data.api.ApiHelper
import com.nyakshoot.stafftrackersimplenavigation.data.models.Admin
import com.nyakshoot.stafftrackersimplenavigation.data.models.AdminResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val apiHelper: ApiHelper) : ViewModel() {
    private val _authState = MutableStateFlow(AdminResponse("", ""))
    val authState: StateFlow<AdminResponse> = _authState

    fun login(admin: Admin) {
        viewModelScope.launch {
            try {
                val authState = apiHelper.login(admin)
                _authState.tryEmit(authState)
            } catch (e: Exception) {
                Log.e("login", "Error fetching data", e)
            }
        }
    }
}