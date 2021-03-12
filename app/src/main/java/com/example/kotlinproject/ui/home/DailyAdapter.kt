package com.example.kotlinproject.ui.home

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.R
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.Daily
import com.example.kotlinproject.ui.baseHome.MainActivity

class DailyAdapter ( var homeViewModel: HomeViewModel) : RecyclerView.Adapter<DailyAdapter.MyViewHolder>() {
    lateinit var models: List<Daily>

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       private var time = itemView.findViewById<TextView>(R.id.currentTime)
        private var temp = itemView.findViewById<TextView>(R.id.currentTemp)
        private var tempUInt = itemView.findViewById<TextView>(R.id.tempUnit)
        private var description = itemView.findViewById<TextView>(R.id.description)
        private var icon = itemView.findViewById<ImageView>(R.id.currentModeImg)

        @RequiresApi(Build.VERSION_CODES.O)
        fun binding(hourly: Daily) {
            homeViewModel.loadImage(icon,hourly.weather[0].icon)
            description.text = hourly.weather[0].description
            temp.text = hourly.temp.day.toString()
            time.text = homeViewModel.formateDate(hourly.dt)
            tempUInt.text=homeViewModel.getUnites(MainActivity.units)

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = inflater.inflate(R.layout.hourly_item, parent, false)
        return MyViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding(models[position])
    }

    override fun getItemCount(): Int {
        return models.size
    }
}