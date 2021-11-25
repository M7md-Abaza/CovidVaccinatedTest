package com.m7mdabaza.weartest2.retrofit

data class UserData(
    var id: String,
    var accountId: String,
    var medicalCaseName: String,
    var fileUri: String,
    var name: String
)
//https://test-covidcertificate-api.azurewebsites.net/qrme?AccountId=ddf7c394-142b-40f5-9f5b-4af81c192c8b