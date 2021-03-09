package com.example.kotlinproject.ui.favourit

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.R
import com.example.kotlinproject.dataLayer.entity.favtable.FavData
import com.example.kotlinproject.databinding.FragmentFavouritBinding
import com.example.kotlinproject.ui.baseHome.MainActivity
import com.example.kotlinproject.ui.favouriteDetails.FavouriteDetails
import com.example.kotlinproject.ui.map.MapActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Favourit : Fragment() {

    private lateinit var binding :FragmentFavouritBinding
    private lateinit var favouriteViewModel: FavouriteViewModel
    private lateinit var adapter: FavouriteAdapter
    private lateinit var dataList : List<FavData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouritBinding.inflate(inflater, container, false)
        favouriteViewModel= ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application))[FavouriteViewModel::class.java]

        adapter= FavouriteAdapter(favouriteViewModel,MainActivity.units)
        binding.addButton.setOnClickListener{
            if (favouriteViewModel.getOnline(activity!!)) {
            val intent = Intent(activity, MapActivity::class.java)
            startActivity(intent)
            }else{
                Toast.makeText(requireActivity(),getString(R.string.you_are_offline),Toast.LENGTH_SHORT).show()
            }
        }

        favouriteViewModel.getFavDataBase().observe(this,{
            if (it.isNotEmpty()){
                binding.empty.visibility=View.GONE
                loadFavourite(it)
                dataList=it
            }else{
                binding.empty.visibility=View.VISIBLE
            }
        })

        favouriteViewModel.getIntent().observe(this,{
                val intent = Intent(activity, FavouriteDetails::class.java)
                intent.putExtra("lat", "${dataList[it].lat}")
                intent.putExtra("lon", "${dataList[it].lon}")
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_MULTIPLE_TASK
            startActivity(intent)
        })
        favouriteViewModel.getAlertDialogLiveData().observe(this,{
            if (it!=null){
             showAlarm(it.lat.toString(),it.lon.toString())
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

   private fun showAlarm(lat : String,lon: String) {
        val alertDialogBuilder = AlertDialog.Builder(activity!!)
        alertDialogBuilder.setTitle("Are you Sure")
        alertDialogBuilder.setMessage("you want to delete this city")
        alertDialogBuilder.setPositiveButton("Yes") { _, _ ->
            CoroutineScope(Dispatchers.IO).launch {
                favouriteViewModel.deleteOneFav(lat,lon)
            }
        }
        alertDialogBuilder.setNegativeButton("No") { _, _ ->

        }
        alertDialogBuilder.show()
    }

}