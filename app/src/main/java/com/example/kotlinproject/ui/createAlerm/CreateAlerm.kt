package com.example.kotlinproject.ui.createAlerm

import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.ActivityCreateAlermBinding
import java.util.*


class CreateAlerm : AppCompatActivity() {
    lateinit var picker: TimePickerDialog
    lateinit var activityCreateAlermBinding: ActivityCreateAlermBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityCreateAlermBinding= DataBindingUtil.setContentView(
            this,
            R.layout.activity_create_alerm
        )
        activityCreateAlermBinding.timePickerStart.setOnClickListener{


            val cldr: Calendar = Calendar.getInstance()
            val hour: Int = cldr.get(Calendar.HOUR_OF_DAY)
            val minutes: Int = cldr.get(Calendar.MINUTE)

            picker = TimePickerDialog(
                this,
                { tp, sHour, sMinute ->
                    Toast.makeText(this,"$sHour : $sMinute",Toast.LENGTH_SHORT).show()
                    picker.dismiss()
                }, hour, minutes, true
            )
            picker.show()
        }
    }
}