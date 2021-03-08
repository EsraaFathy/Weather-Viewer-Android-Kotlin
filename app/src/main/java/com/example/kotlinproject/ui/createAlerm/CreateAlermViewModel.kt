package com.example.kotlinproject.ui.createAlerm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinproject.dataLayer.DataSourceViewModel
import com.example.kotlinproject.dataLayer.entity.AlertTable

class CreateAlermViewModel(application: Application) : AndroidViewModel(application) {
    val dataSourceViewModel=DataSourceViewModel(application)
    val dataSavedOrNot=MutableLiveData<Boolean>()
     private fun saveAlert(alertTable: AlertTable){
            dataSourceViewModel.saveAlert(alertTable)
    }
    fun getAllAlerts(): LiveData<List<AlertTable>> {
        return dataSourceViewModel.getAllAlerts()
    }

    fun saveData( title: String?,
                  startTime: Long?,
                  startDate: Long?,
                  endTime: Long?,
                  endDate: Long?,
                  reputation: Boolean){
        if (title != null || startTime!= null
            || startDate != null || endTime != null
            || endDate!= null){
            saveAlert(AlertTable(title = title!!,startTime = startTime!!,startDate = startDate!!,
                endTime = endTime!!,endDate = endDate!!,reputation = reputation))
            dataSavedOrNot.value=true
        }else{
            dataSavedOrNot.value=false
        }
    }

    fun getDataSavedOrNot():LiveData<Boolean>{
        return dataSavedOrNot
    }
}