package com.nyakshoot.stafftrackersimplenavigation.data.viewmodel

import android.content.Context
import android.os.Environment
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyakshoot.stafftrackersimplenavigation.data.api.ApiHelper
import com.nyakshoot.stafftrackersimplenavigation.data.models.Document
import com.nyakshoot.stafftrackersimplenavigation.utils.saveFile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DocumentViewModel(private val apiHelper: ApiHelper) : ViewModel() {
    private val _documents = MutableStateFlow<List<Document>>(emptyList())
    val documents: StateFlow<List<Document>> = _documents


    private val env =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

    fun getEmployeeDocuments(employeeId: String) {
        viewModelScope.launch {
            try {
                val documents = apiHelper.getEmployeeDocuments(employeeId)
                _documents.tryEmit(documents)
            } catch (e: Exception) {
                Log.e("getEmployees", "Error fetching data", e)
            }
        }
    }

    fun loadDocument(document: Document, context: Context) {
        viewModelScope.launch {
            try {
                val responseBody = apiHelper.loadDocument(document.document_url).body()
                Log.d("Penis_document_view_model", responseBody.toString())
                saveFile(
                    responseBody,
                    env.toString(),
                    document.document_name,
                    document.document_url,
                    context
                )
            } catch (e: Exception) {
                Log.e("uploadDocument", "Ошибка при загрузке документа", e)
            }
        }
    }
}