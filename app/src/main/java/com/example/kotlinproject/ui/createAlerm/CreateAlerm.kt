package com.example.kotlinproject.ui.createAlerm

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.ActivityCreateAlermBinding
import java.util.*


class CreateAlerm : AppCompatActivity() {
    lateinit var activityCreateAlermBinding: ActivityCreateAlermBinding
    lateinit var createAlermViewModel: CreateAlermViewModel
    var zhour: Int? = null
    var zmin: Int? = null
    var zmonth: Int? = null
    var zday: Int? = null
    var zyear: Int? = null
    var ahour: Int? = null
    var amin: Int? = null
    var amonth: Int? = null
    var aday: Int? = null
    var ayear: Int? = null
    private val startCalendar: Calendar = Calendar.getInstance()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityCreateAlermBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_create_alerm
        )

        createAlermViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)
        )[CreateAlermViewModel::class.java]

        activityCreateAlermBinding.timePickerStart.setOnClickListener {
            getTime()

        }
        activityCreateAlermBinding.timePickerend.setOnClickListener {
        }
        activityCreateAlermBinding.datePickerStart.setOnClickListener {
            getDate()
        }

        activityCreateAlermBinding.datePickerEnd.setOnClickListener {
            getDate()
        }
//        fun setAlaram(activity: Activity, hour:Int, min:Int, month:Int, day:Int, year:Int) {
        activityCreateAlermBinding.saveButton.setOnClickListener {
            createAlermViewModel.saveData(
                title = activityCreateAlermBinding.alertTitle.text.toString(),
                getResources().getStringArray(R.array.alert)[activityCreateAlermBinding.alertSpinner.selectedItemPosition],
                reputation = activityCreateAlermBinding.checkboxReputation.isChecked,
                time = "${activityCreateAlermBinding.startTimeText.text}  ${activityCreateAlermBinding.startDateText.text}"
            )
        }

        createAlermViewModel.idLiveData.observe(this,{
            if (it!=null){
                createAlermViewModel.setAlaram(this, zhour!!, zmin!!, zmonth!!, zday!!, zyear!!
                    ,type = getResources().getStringArray(R.array.alert)[activityCreateAlermBinding.alertSpinner.selectedItemPosition],
                    reputation = activityCreateAlermBinding.checkboxReputation.isChecked,it)
            }
        })

        createAlermViewModel.getDataSavedOrNot().observe(this, {
            if (it) {
                Toast.makeText(this, getString(R.string.data_saaved), Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, getString(R.string.there_is_missed_data), Toast.LENGTH_SHORT)
                    .show()

            }
        })
    }



    @SuppressLint("SetTextI18n")
    private fun getDate() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            this, { _, year, monthOfYear, dayOfMonth ->
                zmonth = monthOfYear + 1
                zyear = year
                zday = dayOfMonth
                startCalendar[Calendar.MONTH] = month - 1
                startCalendar[Calendar.DATE] = dayOfMonth
                startCalendar[Calendar.YEAR] = year
                activityCreateAlermBinding.startDateText.text = "$dayOfMonth-$month$year"
            }, year, month, day
        )
        dpd.datePicker.minDate = System.currentTimeMillis() - 1000
        dpd.show()
    }

    @SuppressLint("SetTextI18n")
    private fun getTime() {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR)
        val minute = c.get(Calendar.MINUTE)
        val datetime = Calendar.getInstance()
        val tpd = TimePickerDialog(
            this,
            { view, h, m ->
                c[Calendar.HOUR_OF_DAY] = h
                c[Calendar.MINUTE] = m
                if (c.timeInMillis >= datetime.timeInMillis) {
                    zhour = h
                    zmin = m
                    startCalendar.set(Calendar.HOUR_OF_DAY, h)
                    startCalendar.set(Calendar.MINUTE, m)
                    startCalendar[Calendar.SECOND] = 0
                    activityCreateAlermBinding.startTimeText.text = "$h:$m"

                } else {
                }
            }, hour, minute, false
        )
        tpd.show()
    }

//    @SuppressLint("SetTextI18n")
//    private fun getDateEnd() {
//        val c = Calendar.getInstance()
//        val year = c.get(Calendar.YEAR)
//        val month = c.get(Calendar.MONTH)
//        val day = c.get(Calendar.DAY_OF_MONTH)
//        val dpd = DatePickerDialog(
//            this, { _, year, monthOfYear, dayOfMonth ->
//                amonth = monthOfYear + 1
//                ayear = year
//                aday = dayOfMonth
//                startCalendar[Calendar.MONTH] = month - 1
//                startCalendar[Calendar.DATE] = dayOfMonth
//                startCalendar[Calendar.YEAR] = year
//                activityCreateAlermBinding.endDateText.text = "$dayOfMonth-$month$year"
//            }, year, month, day
//        )
//        dpd.datePicker.minDate = System.currentTimeMillis() - 1000
//        dpd.show()
//    }
//
//    @SuppressLint("SetTextI18n")
//    private fun getTimeEnd() {
//        val c = Calendar.getInstance()
//        val hour = c.get(Calendar.HOUR)
//        val minute = c.get(Calendar.MINUTE)
//        val datetime = Calendar.getInstance()
//        val tpd = TimePickerDialog(
//            this,
//            { view, h, m ->
//                c[Calendar.HOUR_OF_DAY] = h
//                c[Calendar.MINUTE] = m
//                if (c.timeInMillis >= datetime.timeInMillis) {
//                    ahour = h
//                    amin = m
//                    startCalendar.set(Calendar.HOUR_OF_DAY, h)
//                    startCalendar.set(Calendar.MINUTE, m)
//                    startCalendar[Calendar.SECOND] = 0
//                    activityCreateAlermBinding.endTimeText.text = "$h:$m"
//
//                } else {
//                }
//            }, hour, minute, false
//        )
//        tpd.show()
//    }
}


