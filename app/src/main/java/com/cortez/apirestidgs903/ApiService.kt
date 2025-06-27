package com.cortez.apirestidgs903

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @GET("posts")
    suspend fun getStudents(): List<Student>

    @POST("posts")
    fun addStudent(@Body misDatos: Student): Call<StudentResponse>

    @PUT("posts/{id}")
    fun updateStudent(@Path("id") id: Int, @Body misDatos: Student): Call<StudentResponse>

    @DELETE("posts/{id}")
    fun deleteStudent(@Path("id") id: Int): Call<Void>

    companion object {
        private var apiService: ApiService?= null

        private var url: String = "https://jsonplaceholder.typicode.com/"  // Corregí el typo en la URL

        //instancia retrofit
        fun getInstance(): ApiService{  // Corregí el nombre del método
            if (apiService == null){
                apiService = Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    //Instancia a la interfaz
                    .create(ApiService::class.java)
            }
            return apiService!!
        }

    }
}