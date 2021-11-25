package com.m7mdabaza.weartest2.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIs {

    //https://test-covidcertificate-api.azurewebsites.net/qrme?AccountId=ddf7c394-142b-40f5-9f5b-4af81c192c8b


    private const val BASE_URL = "https://test-covidcertificate-api.azurewebsites.net/"


    private fun getApi(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val DATA_OBJECT : ApiDataInterface = getApi().create(ApiDataInterface::class.java)
}