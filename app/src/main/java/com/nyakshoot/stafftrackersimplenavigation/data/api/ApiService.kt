package com.nyakshoot.stafftrackersimplenavigation.data.api

import com.nyakshoot.stafftrackersimplenavigation.data.models.Admin
import com.nyakshoot.stafftrackersimplenavigation.data.models.AdminResponse
import com.nyakshoot.stafftrackersimplenavigation.data.models.Document
import com.nyakshoot.stafftrackersimplenavigation.data.models.Employee
import com.nyakshoot.stafftrackersimplenavigation.data.models.NewEmployee
import com.nyakshoot.stafftrackersimplenavigation.data.models.Photo
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("/login")
    suspend fun login(@Body admin: Admin): AdminResponse

    @POST("/employees")
    suspend fun newEmployee(@Body employee: NewEmployee)

    @GET("/employees/active")
    suspend fun getActiveEmployees(): List<Employee>

    @GET("/employees/fired")
    suspend fun getFiredEmployees(): List<Employee>

    @DELETE("/employees/{employee_id}")
    suspend fun firedEmployee(@Path("employee_id") employeeId: String)

    @GET("/photos/{employee_id}")
    suspend fun getEmployeePhotos(@Path("employee_id") employeeId: String): List<Photo>

    @GET("/employee_document/{employee_id}")
    suspend fun getEmployeeDocuments(@Path("employee_id") employeeId: String): List<Document>

    @GET("/photos/{photo_url}")
    suspend fun loadPhoto(@Path("photo_url") photoUrl: String): Response<ResponseBody>

    @GET("/documents/{document_url}")
    suspend fun loadDocument(@Path("document_url") documentUrl: String): Response<ResponseBody>

    @Multipart
    @POST
    suspend fun uploadDocument(
        @Part("document_name") documentName: String,
        @Part file: MultipartBody.Part
    )

    @GET("/test")
    suspend fun getTestApi(): Employee
}
