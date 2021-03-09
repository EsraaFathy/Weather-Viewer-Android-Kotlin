package com.example.kotlinproject.ui.alert

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.R
import com.example.kotlinproject.dataLayer.entity.AlertTable

class CurrentAlertAdaapter(var favViewModel: AlertViewModel) : RecyclerView.Adapter<CurrentAlertAdaapter.MyViewHolder>() {
     var models: List<AlertTable> = emptyList()

    inner class MyViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val event : TextView = itemView.findViewById(R.id.event_Data)
        private val start : TextView = itemView.findViewById(R.id.start_data)
        private val description : TextView = itemView.findViewById(R.id.description_data)
        private val close : ImageView=itemView.findViewById(R.id.close_alert)


        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.O)
        fun binding(allData: AlertTable) {
            event.text=allData.title
            description.text=allData.type
            start.text=allData.time
            close.setOnClickListener {
                favViewModel.cancelAlert.value=allData.id
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = inflater.inflate(R.layout.alarm_item, parent, false)
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