package com.example.kotlinproject.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.R
import com.example.kotlinproject.dataLayer.entity.oneCallEntity.Hourly

//class HourlyAdabter {
//}
class HourlyAdabter(var models : MutableList<Hourly>) : RecyclerView.Adapter<HourlyAdabter.MyViewHolder>() {

    open class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        var image : ImageView =view.findViewById(R.id.imageView)
//        var title : TextView =view.findViewById(R.id.textView)
//        var desc : TextView =view.findViewById(R.id.textView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.hourly_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = models[position]
//        holder.title.text = movie.getTitle()
//        holder.desc.text = movie.getDesc()
//        holder.image.setImageResource(movie.getImage())
    }

    override fun getItemCount(): Int {
        return models.size
    }
}