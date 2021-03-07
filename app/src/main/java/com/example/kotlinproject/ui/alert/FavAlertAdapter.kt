package com.example.kotlinproject.ui.alert

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.R
import com.example.kotlinproject.dataLayer.entity.favtable.Alert
import com.example.kotlinproject.ui.GeneralFunctions

class FavAlertAdapter(var favViewModel: AlertViewModel) : RecyclerView.Adapter<FavAlertAdapter.MyViewHolder>() {
    var models: List<Alert> = emptyList()
    val generalFunctions : GeneralFunctions = GeneralFunctions()

    inner class MyViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val event : TextView = itemView.findViewById(R.id.event_Data)
        private val start : TextView = itemView.findViewById(R.id.start_data)
        private val end : TextView = itemView.findViewById(R.id.end_data)
        private val description : TextView = itemView.findViewById(R.id.description_data)

        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.O)
        fun binding(allData: Alert) {
            event.text=allData.event
            description.text=allData.description
            start.text= "${generalFunctions.formateDate(allData.start!!)} ${generalFunctions.formateTime(allData.start)}"
            end.text= "${generalFunctions.formateDate(allData.end!!)} ${generalFunctions.formateTime(allData.end)}"

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