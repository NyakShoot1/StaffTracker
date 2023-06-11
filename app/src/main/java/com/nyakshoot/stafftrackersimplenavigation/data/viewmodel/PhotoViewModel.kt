package com.nyakshoot.stafftrackersimplenavigation.data.viewmodel

import android.content.Context
import android.os.Environment
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyakshoot.stafftrackersimplenavigation.data.api.ApiHelper
import com.nyakshoot.stafftrackersimplenavigation.data.models.Document
import com.nyakshoot.stafftrackersimplenavigation.data.models.Photo
import com.nyakshoot.stafftrackersimplenavigation.utils.saveFile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PhotoViewModel (private val apiHelper: ApiHelper) : ViewModel(){
    private val _photos = MutableStateFlow<List<Photo>>(emptyList())
    val photos: StateFlow<List<Photo>> = _photos

    private val env =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

    fun getEmployeePhoto(employeeId: String) {
        viewModelScope.launch {
            try {
                val photos = apiHelper.getEmployeePhotos(employeeId)
                _photos.tryEmit(photos)
            } catch (e: Exception) {
                Log.e("getEmployeePhoto", "Error fetching data", e)
            }
        }
    }

    fun loadPhoto(photo: Photo, context: Context) {
        viewModelScope.launch {
            try {
                val responseBody = apiHelper.loadPhoto(photo.photo_url).body()
                Log.d("Penis_document_view_model", responseBody.toString())
                saveFile(
                    responseBody,
                    env.toString(),
                    photo.photo_name,
                    photo.photo_url,
                    context
                )
            } catch (e: Exception) {
                Log.e("uploadDocument", "Ошибка при загрузке документа", e)
            }
        }
    }
}