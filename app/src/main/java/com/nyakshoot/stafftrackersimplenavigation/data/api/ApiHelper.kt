package com.nyakshoot.stafftrackersimplenavigation.data.api

import com.google.gson.JsonObject
import com.nyakshoot.stafftrackersimplenavigation.data.models.NewEmployee
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import java.io.File

class ApiHelper(private val apiService: ApiService) {

    suspend fun newEmployee(employee: NewEmployee) = apiService.newEmployee(employee)

    suspend fun getActiveEmployees() = apiService.getActiveEmployees()

    suspend fun getFiredEmployees() = apiService.getFiredEmployees()

    suspend fun firedEmployee(employeeId: String) = apiService.firedEmployee(employeeId)

    suspend fun getEmployeePhotos(employeeId: String) = apiService.getEmployeePhotos(employeeId)

    suspend fun getEmployeeDocuments(employeeId: String) =
        apiService.getEmployeeDocuments(employeeId)

    suspend fun loadPhoto(photoUrl: String) = apiService.loadPhoto(photoUrl)

    suspend fun loadDocument(documentUrl: String) = apiService.loadDocument(documentUrl)

    suspend fun uploadDocument(documentName: String, file: MultipartBody.Part) =
        apiService.uploadDocument(documentName, file)

    suspend fun test() = apiService.getTestApi()
}