package com.nyakshoot.stafftrackersimplenavigation.data.api

import com.nyakshoot.stafftrackersimplenavigation.data.models.Document
import com.nyakshoot.stafftrackersimplenavigation.data.models.Employee
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/employees")
    suspend fun getEmployees(): List<Employee>

    @GET("/employee_document/{employee_id}")
    suspend fun getEmployeeDocuments(@Path("employee_id") employeeId: String): List<Document>

    @GET("/documents/{document_url}")
    suspend fun loadDocument(@Path("document_url") documentUrl: String): Response<ResponseBody>

    @GET("/test")
    suspend fun getTestApi(): Employee
}
