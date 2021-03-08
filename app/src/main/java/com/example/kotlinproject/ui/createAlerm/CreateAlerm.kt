package com.example.kotlinproject.ui.createAlerm

import android.annotation.SuppressLint
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
    lateinit var picker: TimePickerDialog
    lateinit var pickerDate: DatePickerDialog
    lateinit var activityCreateAlermBinding: ActivityCreateAlermBinding
    lateinit var createAlermViewModel: CreateAlermViewModel
    private val startCalendar : Calendar = Calendar.getInstance()
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityCreateAlermBinding= DataBindingUtil.setContentView(
            this,
            R.layout.activity_create_alerm
        )

        createAlermViewModel= ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)
        )[CreateAlermViewModel::class.java]

        var secondsTimeStarts: Long?=null
        activityCreateAlermBinding.timePickerStart.setOnClickListener{
            val cldr: Calendar = Calendar.getInstance()
            val hour: Int = cldr.get(Calendar.HOUR_OF_DAY)
            val minutes: Int = cldr.get(Calendar.MINUTE)
            cldr.set(Calendar.SECOND, 0)

            picker = TimePickerDialog(
                this,
                { clr, sHour, sMinute ->
                    activityCreateAlermBinding.startTimeText.text = "$sHour : $sMinute"

                    secondsTimeStarts = cldr.timeInMillis
                    startCalendar.set(Calendar.HOUR,sHour)
                    startCalendar.set(Calendar.MINUTE,sMinute)
                    startCalendar.set(Calendar.SECOND,0)

                    Log.d("TAG HI", "$secondsTimeStarts")
                    picker.dismiss()
                }, hour, minutes, true
            )
            picker.show()
        }
        var secondsDateStart: Long?=null
        activityCreateAlermBinding.timePickerend.setOnClickListener{
            val cldr: Calendar = Calendar.getInstance()
            val hour: Int = cldr.get(Calendar.HOUR_OF_DAY)
            val minutes: Int = cldr.get(Calendar.MINUTE)

            picker = TimePickerDialog(
                this,
                { tp, sHour, sMinute ->
                    activityCreateAlermBinding.endTimeText.text = "$sHour : $sMinute"
                    secondsDateStart = cldr.timeInMillis
//                    startCalendar.set(Calendar.HOUR,sHour)
//                    startCalendar.set(Calendar.MINUTE,sMinute)
//                    startCalendar.set(Calendar.SECOND,0)

                    picker.dismiss()
                }, hour, minutes, true
            )
            picker.show()
        }
        var secondsTimeEnd: Long?=null
        activityCreateAlermBinding.datePickerStart.setOnClickListener{
            val cldr: Calendar = Calendar.getInstance()
            val day: Int = cldr.get(Calendar.DAY_OF_WEEK)
            val month: Int = cldr.get(Calendar.MONTH)
            val year: Int = cldr.get(Calendar.YEAR)
            pickerDate = DatePickerDialog(this, { _, year, month, day ->
                activityCreateAlermBinding.startDateText.text = "$day - $month - $year"

                startCalendar.set(Calendar.DAY_OF_WEEK,day)
                startCalendar.set(Calendar.MONTH,month)
                startCalendar.set(Calendar.YEAR,year)


                secondsTimeEnd = cldr.timeInMillis

            }, year, month, day)
            pickerDate.show()
        }

        var secondsDateEnd: Long?=null
        activityCreateAlermBinding.datePickerEnd.setOnClickListener{
            val cldr: Calendar = Calendar.getInstance()
            val day: Int = cldr.get(Calendar.DAY_OF_WEEK)
            val month: Int = cldr.get(Calendar.MONTH)
            val year: Int = cldr.get(Calendar.YEAR)
            pickerDate = DatePickerDialog(this, { _, year, month, day ->
                activityCreateAlermBinding.endDateText.text = "$day - $month - $year"
                secondsDateEnd = cldr.timeInMillis

            }, year, month, day)
            pickerDate.show()
        }

        activityCreateAlermBinding.saveButton.setOnClickListener{
            createAlermViewModel.saveData(
                title = activityCreateAlermBinding.alertTitle.text.toString(),
                startTime = secondsTimeStarts,
                startDate = secondsDateStart,
                endTime = secondsTimeEnd,
                endDate = secondsDateEnd,
                reputation = activityCreateAlermBinding.checkboxReputation.isChecked
            )
        }

        createAlermViewModel.getDataSavedOrNot().observe(this, {
            if (it) {
                createAlerm(startCalendar.timeInMillis)
                Toast.makeText(this, getString(R.string.data_saaved), Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, getString(R.string.there_is_missed_data), Toast.LENGTH_SHORT)
                    .show()

            }
        })
    }

    private fun createAlerm(secondsTimeStarts: Long) {
        Log.d("TAG","${startCalendar.timeInMillis}")
        Log.d("TAG  Calendar.MINUTE","${startCalendar.get(Calendar.MINUTE)}")
        Log.d("TAG  Calendar.HOUR","${startCalendar.get(Calendar.HOUR)}")
        createAlermViewModel.startAlert(this, secondsTimeStarts)
    }
}


