package com.m7mdabaza.weartest2.ui


import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

import android.os.Bundle
import android.util.Log
import android.view.View
import com.m7mdabaza.weartest2.R
import com.m7mdabaza.weartest2.retrofit.APIs
import com.m7mdabaza.weartest2.retrofit.UserData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : Activity() {

    var userDataList: ArrayList<UserData> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (checkConnectivity()) {
           /* val imageUrl =
                "https://devseapatient.blob.core.windows.net/ddf7c394-142b-40f5-9f5b-4af81c192c8b/medicaldata/1c31e6e6-7632-4d0e-8413-dc496bc97d41.png?fbclid=IwAR3HNJ4DHYGXojEilePjVdusx02O2_eK0vydSN_rVZFnzSylprPFK4T8g88" //response.body()!!.fileUri
            Picasso.get().load(imageUrl).into(image_item)*/

            getUserData()

        } else {
            connectionStatus.visibility = View.VISIBLE
            loadingProgress.visibility = View.GONE
        }

        /* var position = 0
         name_item.text = userDataList[position].name
         details_item.text = userDataList[position].medicalCaseName


         right_btn.setOnClickListener {
             if (position == userDataList.size - 1) {
                 position = 0
             } else {
                 position++
             }
             name_item.text = userDataList[position].name
             details_item.text = userDataList[position].medicalCaseName
         }

         left_btn.setOnClickListener {
             if (position == 0) {
                 position = userDataList.size - 1
             } else {
                 position--
             }
             name_item.text = userDataList[position].name
             details_item.text = userDataList[position].medicalCaseName
         }*/
    }

    private fun getUserData() {
        APIs.DATA_OBJECT.getUserData().enqueue(object : Callback<UserData> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<UserData>,
                response: Response<UserData>
            ) {
                if (response.isSuccessful) {

                    name_item.text = response.body()!!.name
                    details_item.text = response.body()!!.medicalCaseName
                    val imageUrl: String = response.body()!!.fileUri
                    Picasso.get().load(imageUrl).into(image_item)

                    dataLayout.visibility = View.VISIBLE
                    loadingProgress.visibility = View.GONE

                    userDataList.add(
                        UserData(
                            response.body()!!.id,
                            response.body()!!.accountId,
                            response.body()!!.name,
                            response.body()!!.fileUri,
                            response.body()!!.medicalCaseName,

                            )
                    )
                    Log.d(ContentValues.TAG, "MAA onResponse: " + response.body()!!.name)


                }

                Log.d(ContentValues.TAG, "MAA getUserData:" + userDataList.size)

            }

            @SuppressLint("SetTextI18n")
            override fun onFailure(call: Call<UserData>, t: Throwable) {
                Log.d(ContentValues.TAG, "MAA onFailure : ${t.localizedMessage}")
                loadingProgress.visibility = View.GONE
                connectionStatus.text = "Server Error, We will back soon"
                connectionStatus.visibility = View.VISIBLE
            }

        })

    }

    // to check if there is internet connection or not by return true or false
    private fun checkConnectivity(): Boolean {
        val connectivityManager = this.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = Objects.requireNonNull(connectivityManager)
            .getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    true
                }
                else -> capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
            }
        }
        return false
    }

}