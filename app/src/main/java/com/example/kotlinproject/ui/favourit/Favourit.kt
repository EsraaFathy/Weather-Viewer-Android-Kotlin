package com.example.kotlinproject.ui.favourit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.R
import com.example.kotlinproject.dataLayer.entity.favtable.FavData
import com.example.kotlinproject.databinding.FragmentFavouritBinding
import com.example.kotlinproject.ui.favouriteDetails.FavouriteDetails
import com.example.kotlinproject.ui.map.MapActivity


class Favourit : Fragment() {

    lateinit var binding :FragmentFavouritBinding
    lateinit var favouriteViewModel: FavouriteViewModel
    lateinit var adapter: FavouriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouritBinding.inflate(inflater, container, false)
        favouriteViewModel= FavouriteViewModel(activity!!)
        adapter= FavouriteAdapter(activity!!)
        binding.addButton.setOnClickListener{
            val intent = Intent(activity, MapActivity::class.java)
            startActivity(intent)
        }

        favouriteViewModel.getFavDataBase().observe(this,{
            if (it.isNotEmpty()){
                loadFavourite(it)
            }
        })




        return binding.root
    }

    private fun loadFavourite(it: List<FavData>) {
        val lay : RecyclerView.LayoutManager= LinearLayoutManager(activity)
        binding.recyclerViewFav.layoutManager=lay
        adapter.models=it
        binding.recyclerViewFav.adapter=adapter
    }


}