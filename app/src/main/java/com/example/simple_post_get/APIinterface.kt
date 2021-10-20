package com.example.simple_post_get
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
interface APIinterface {

        @GET("/custom-people/")
        fun getname(): Call<ArrayList<People>>

    @POST("//custom-people//")
    fun addUser(@Body userData: People): Call<People>


    }
class People ( var name: String)


