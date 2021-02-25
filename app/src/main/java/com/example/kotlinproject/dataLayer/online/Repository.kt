package com.example.kotlinproject.dataLayer.online

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinproject.dataLayer.entity.currentModel.ModelCurent
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.AllData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(private val apiInterface : ApiInterface) {
    private var currentdata: MutableLiveData<ModelCurent> = MutableLiveData<ModelCurent>()
    private var oneCallData: MutableLiveData<AllData> = MutableLiveData<AllData>()

    //    private const val BASE_URL = "http://api.openweathermap.org/data/2.5/weather?lat=30.590426&lon=31.502410&lang=en&appid=517a14f849e519bb4fa84cdbd4755f56"
//    val repository: Repository= Repository(ApiClient.apiService)
    //https://api.openweathermap.org/data/2.5/onecall?lat=33.441792&lon=-94.037689&exclude=minutely&lang=ar&units=standard&appid=517a14f849e519bb4fa84cdbd4755f56
     private fun getCurrent(lat: String,lon: String,lang: String, appid: String) = apiInterface.getCurrent(lat,lon,lang,appid)
     private fun getOneCall(lat: String,lon: String,lang: String, appid: String,exclude :String,units :String) =
         apiInterface.getOneCall(lat,lon,lang,appid,exclude,units)
//         apiInterface.getOneCall(lat,lon,lang,appid,"minutely")




    public fun loadCurrent(lat: String,lon: String,lang: String, appid: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val data = getCurrent(lat,lon,lang,appid)
            data.enqueue(object : retrofit2.Callback<ModelCurent?> {
                override fun onResponse(
                    call: Call<ModelCurent?>,
                    response: Response<ModelCurent?>
                ) {
                    currentdata.value = response.body()
                }

                override fun onFailure(call: Call<ModelCurent?>, t: Throwable) {
                }
            })

        }
    }
     fun loadOneCall(lat: String,lon: String,lang: String, appid: String,exclude :String,units :String) {
        CoroutineScope(Dispatchers.IO).launch {
            val data = getOneCall(lat,lon,lang,appid,exclude,units)
            data.enqueue(object : Callback<AllData?> {
                override fun onResponse(call: Call<AllData?>, response: Response<AllData?>) {
                    oneCallData.value = response.body()
                    Log.d("TAG", response.body()!!.timezone)
                    Log.d("TAG", "response.body()!!.timezone")
                }

                override fun onFailure(call: Call<AllData?>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })

        }
    }



     fun getCurentData() : LiveData<ModelCurent>{
        return currentdata;
    }
     fun getOneCalltData() : LiveData<AllData>{
        return oneCallData;
    }



}