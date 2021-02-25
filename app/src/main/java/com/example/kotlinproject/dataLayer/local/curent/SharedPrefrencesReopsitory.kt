package com.example.kotlinproject.dataLayer.local.curent

import android.content.Context
import androidx.lifecycle.LiveData

import com.example.kotlinproject.dataLayer.entity.oneCallEntity.Current
class SharedPrefrencesReopsitory(context: Context) {
    private val currentFile : CurrentFileSB = CurrentFileSB(context)
    fun saveCurrent(current: Current,timezone:String){
        currentFile.saveCurrent(current,timezone)
    }

    fun getLoca(): LiveData<Current>{
        currentFile.loadLocalCurrent()
        return currentFile.getLocalCurrent()
    }
}