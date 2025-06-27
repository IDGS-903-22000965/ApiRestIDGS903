package com.cortez.apirestidgs903

import android.telecom.Call
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @GET("posts")
    suspend fun getStudents(): List<Student>

    @cd ("posts")
    fun addStudent(@Body misDatos: Student): Call<StudentResponse>

    @PUT("posts/{id}")
    fun updateStudent(@Path("id") id: Int, @Body misDatos: Student): Call<StudentResponse>

    @DELETE("posts/{id}")
    fun deleteStudent(@Path("id") id: Int): Call<Void>

    companion object {
        private var apiService: ApiService?= null

        private  var url: String = "https://jsonplacerholder.typecode.com/"

        //insyancia retrofit
        fun getIstance(): ApiService{
            if (apiService == null){
                apiService = Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    //Instacia a la interfaz
                    .create(ApiService::class.java)
            }
            return apiService!!
        }

    }
}