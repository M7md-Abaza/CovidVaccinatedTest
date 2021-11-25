package com.m7mdabaza.weartest2.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface ApiDataInterface {

    @GET("/qrme?AccountId=ddf7c394-142b-40f5-9f5b-4af81c192c8b")
    fun getUserData(): Call<UserData>
}