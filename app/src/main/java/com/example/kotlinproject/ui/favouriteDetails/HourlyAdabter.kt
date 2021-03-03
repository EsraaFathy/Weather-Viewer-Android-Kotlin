package com.example.kotlinproject.ui.favouriteDetails

import android.app.Application
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.R
import com.example.kotlinproject.dataLayer.entity.favtable.Hourly


//class HourlyAdabter {
//}
class HourlyAdabter(var context: Application) : RecyclerView.Adapter<HourlyAdabter.MyViewHolder>() {
    lateinit var models: List<Hourly>
    var homeViewModel: DetailsViewModel = DetailsViewModel(context)

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var time = itemView.findViewById<TextView>(R.id.currentTime)
        var temp = itemView.findViewById<TextView>(R.id.currentTemp)
        var description = itemView.findViewById<TextView>(R.id.description)
        var icon = itemView.findViewById<ImageView>(R.id.currentModeImg)

        @RequiresApi(Build.VERSION_CODES.O)
        fun binding(hourly: Hourly) {
            homeViewModel.loadImage(icon,hourly.weather[0].icon)
            description.text = hourly.weather[0].description
            temp.text = hourly.temp.toString()
            time.text = homeViewModel.formateTime(hourly.dt)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = inflater.inflate(R.layout.hourly_item, parent, false)
        return MyViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding(models.get(position))
    }

    override fun getItemCount(): Int {
        return models.size
    }
}