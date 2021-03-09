package com.example.kotlinproject.ui.alert

import android.R
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.dataLayer.entity.AlertTable
import com.example.kotlinproject.databinding.FragmentAlertBinding
import com.example.kotlinproject.ui.createAlerm.AlermRecever
import com.example.kotlinproject.ui.createAlerm.CreateAlerm
import java.util.*


class Alert : Fragment() {

    lateinit var fragmentAlertBinding: FragmentAlertBinding
    lateinit var alertViewModel: AlertViewModel
    lateinit var currentAlertAdaapter: CurrentAlertAdaapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentAlertBinding = FragmentAlertBinding.inflate(inflater, container, false)
        alertViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[AlertViewModel::class.java]
        val spinnerArray = ArrayList<String>()
        currentAlertAdaapter = CurrentAlertAdaapter(alertViewModel)
        val lay: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        fragmentAlertBinding.listCurrent.layoutManager = lay
        fragmentAlertBinding.listCurrent.adapter=currentAlertAdaapter

        fragmentAlertBinding.addAlertButton.setOnClickListener {
            val intent =Intent(activity,CreateAlerm::class.java)
            startActivity(intent)
        }

        alertViewModel.gerAlertTable().observe(this,{
            showRecyclel(it)
        })

        alertViewModel.getAlertSetting().observe(this,{
            if (it == "OFF"){
                fragmentAlertBinding.enableOrNot.isChecked=false
                Log.d("TAG", "offff    $it")
            }else{
                fragmentAlertBinding.enableOrNot.isChecked=true
                Log.d("TAG","onnnn    $it")
            }
        })


        fragmentAlertBinding.enableOrNot.setOnClickListener {
            alertViewModel.saveAlertSetting(fragmentAlertBinding.enableOrNot.text.toString())
            Log.d("TAG",fragmentAlertBinding.enableOrNot.text.toString())
        }

        alertViewModel.cancelAlert.observe(this,{
            alertViewModel.cancelAlert(activity!!,it)
        })

        return fragmentAlertBinding.root
    }


//    private fun showRecyclelFav(alerts: List<com.example.kotlinproject.dataLayer.entity.favtable.Alert>?) {
//        if (alerts != null) {
//            fragmentAlertBinding.empty.visibility=View.INVISIBLE
//            fragmentAlertBinding.listFav.visibility=View.VISIBLE
//            fragmentAlertBinding.listCurrent.visibility=View.INVISIBLE
//            favAlertAdaapter.models = alerts
//            favAlertAdaapter.notifyDataSetChanged()
//        }else{
//            fragmentAlertBinding.empty.visibility=View.VISIBLE
//            fragmentAlertBinding.listFav.visibility=View.INVISIBLE
//            fragmentAlertBinding.listCurrent.visibility=View.INVISIBLE
//        }
//    }
//
    private fun showRecyclel(alerts: List<AlertTable>?) {
        if (alerts != null){
            fragmentAlertBinding.empty.visibility=View.INVISIBLE
            fragmentAlertBinding.listCurrent.visibility=View.VISIBLE
            currentAlertAdaapter.models = alerts
            currentAlertAdaapter.notifyDataSetChanged()
        }else{
            fragmentAlertBinding.empty.visibility=View.VISIBLE
            fragmentAlertBinding.listCurrent.visibility=View.INVISIBLE
        }

    }

    fun startAlert() {

        val myIntent = Intent(activity, AlermRecever::class.java)
        val pendingIntent = PendingIntent.getBroadcast(activity, 1, myIntent, 0)
        val alarmManager: AlarmManager =
            activity!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.add(Calendar.SECOND, 400)

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, 1000, pendingIntent)

    }

    fun cancelAlarm() {
        val myIntent = Intent(activity, AlermRecever::class.java)
        val pendingIntent = PendingIntent.getBroadcast(activity, 1, myIntent, 0)
        val alarmManager: AlarmManager =
            activity!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)

    }

}