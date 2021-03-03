package com.example.kotlinproject.ui.favourit

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.R
import com.example.kotlinproject.dataLayer.entity.favtable.FavData
import com.example.kotlinproject.ui.favouriteDetails.FavouriteDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FavouriteAdapter(var context: Application) : RecyclerView.Adapter<FavouriteAdapter.MyViewHolder>() {
    lateinit var models: List<FavData>
    var homeViewModel: FavouriteViewModel = FavouriteViewModel(context)

    inner class MyViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView) {
        var temp = itemView.findViewById<TextView>(R.id.currentTemp)
        var description = itemView.findViewById<TextView>(R.id.description)
        var time_Zone = itemView.findViewById<TextView>(R.id.time_zone)
        var icon = itemView.findViewById<ImageView>(R.id.currentModeImg)


        @RequiresApi(Build.VERSION_CODES.O)
        fun binding(favData: FavData) {
            homeViewModel.loadImage(icon,favData.current.weather[0].icon)
            description.text = favData.current.weather[0].description
            temp.text = favData.current.temp.toString()
            time_Zone.text=favData.timezone
            itemView.setOnClickListener{
                //"${favData.lat}${favData.lon}"
                val intent = Intent(context, FavouriteDetails::class.java)
                intent.putExtra("lat","${favData.lat}")
                intent.putExtra("lon","${favData.lon}")
                context.startActivity(intent)
            }
            itemView.setOnLongClickListener{
                homeViewModel.showAlarm(favData.lat.toString(),favData.lon.toString())
                true
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = inflater.inflate(R.layout.favourite_item, parent, false)
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