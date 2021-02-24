package com.example.kotlinproject.dataLayer.online

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.example.kotlinproject.R
import com.example.kotlinproject.dataLayer.model.ModelCurent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class Repository(private val apiInterface : ApiInterface) {
    private var allData: MutableLiveData<ModelCurent> = MutableLiveData<ModelCurent>()

    //    private const val BASE_URL = "http://api.openweathermap.org/data/2.5/weather?lat=30.590426&lon=31.502410&lang=en&appid=517a14f849e519bb4fa84cdbd4755f56"
//    val repository: Repository= Repository(ApiClient.apiService)

     private fun getCurrent(lat: String,lon: String,lang: String, appid: String) = apiInterface.getCurrent(lat,lon,lang,appid)




    public fun loadCurrent(lat: String,lon: String,lang: String, appid: String) {
//        apiInterface.getCurrent()
        CoroutineScope(Dispatchers.IO).launch {
//            "30.590426", "31.502410",
//                "en", "517a14f849e519bb4fa84cdbd4755f56"
            val data = getCurrent(lat,lon,lang,appid)
            data.enqueue(object : retrofit2.Callback<ModelCurent?> {
                override fun onResponse(
                    call: Call<ModelCurent?>,
                    response: Response<ModelCurent?>
                ) {
                    allData.value = response.body()
                }

                override fun onFailure(call: Call<ModelCurent?>, t: Throwable) {
                }
            })

        }
    }



    public fun getCurentData() : LiveData<ModelCurent>{
        return allData;
    }



}