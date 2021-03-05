package com.example.kotlinproject.ui.alert

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.FragmentAlertBinding


class Alert : Fragment() {

    lateinit var notificationHelper: NotificationHelper
    lateinit var fragmentAlertBinding :FragmentAlertBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentAlertBinding= FragmentAlertBinding.inflate(inflater, container, false)
        notificationHelper= NotificationHelper(requireActivity())
        for (  a in 1 .. 10){
            val notificationBuilder=notificationHelper.getChanelNotification("testTitle $a","TestBody $a")
            notificationHelper.getManger()!!.notify(a,notificationBuilder.build())
        }


        return fragmentAlertBinding.root
    }

}