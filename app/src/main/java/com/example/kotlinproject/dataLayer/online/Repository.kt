package com.example.kotlinproject.dataLayer.online

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinproject.dataLayer.model.currentModel.ModelCurent
import com.example.kotlinproject.dataLayer.model.onCallModel.OneCallModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class Repository(private val apiInterface : ApiInterface) {
    private var currentdata: MutableLiveData<ModelCurent> = MutableLiveData<ModelCurent>()
    private var oneCallData: MutableLiveData<OneCallModel> = MutableLiveData<OneCallModel>()

    //    private const val BASE_URL = "http://api.openweathermap.org/data/2.5/weather?lat=30.590426&lon=31.502410&lang=en&appid=517a14f849e519bb4fa84cdbd4755f56"
//    val repository: Repository= Repository(ApiClient.apiService)

     private fun getCurrent(lat: String,lon: String,lang: String, appid: String) = apiInterface.getCurrent(lat,lon,lang,appid)
     private fun getOneCall(lat: String,lon: String,lang: String, appid: String,units :String) = apiInterface.getOneCall(lat,lon,lang,appid,"minutely",units)




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
    public fun loadOneCall(lat: String,lon: String,lang: String, appid: String,units :String) {
        CoroutineScope(Dispatchers.IO).launch {
            val data = getOneCall(lat,lon,lang,appid,units)
            data.enqueue(object : retrofit2.Callback<OneCallModel?> {
                override fun onResponse(
                    call: Call<OneCallModel?>,
                    response: Response<OneCallModel?>
                ) {
                    oneCallData.value = response.body()
                }

                override fun onFailure(call: Call<OneCallModel?>, t: Throwable) {
                }
            })

        }
    }



    public fun getCurentData() : LiveData<ModelCurent>{
        return currentdata;
    }
    public fun getOneCalltData() : LiveData<OneCallModel>{
        return oneCallData;
    }



}