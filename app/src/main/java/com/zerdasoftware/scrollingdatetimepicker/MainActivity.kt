package com.zerdasoftware.scrollingdatetimepicker

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(){
    val hourData = MutableLiveData<Int>()
    val minData = MutableLiveData<Int>()
    val strHour = arrayOf("01","02","03","04","05","06","07","08","09","10","11","12")
    val strMin = arrayOf("00","05","10","15","20","25","30","35","40","45","50","55")
    val strAM = arrayOf("AM","PM")
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




       /*
        hourData.observe(this) { hour->
            minData.observe(this) { min->
                getCurrentButton.text = "$hour:$min"
        }
        }
        */

        hourNumberPicker.minValue = 0
        hourNumberPicker.maxValue = strHour.size-1
        hourNumberPicker.displayedValues = strHour

        minNumberPicker.minValue = 0
        minNumberPicker.maxValue = strMin.size-1
        minNumberPicker.displayedValues = strMin

        amNumberPicker.minValue = 0
        amNumberPicker.maxValue = strAM.size-1
        amNumberPicker.displayedValues = strAM

        hourNumberPicker.setOnValueChangedListener { numberPicker, i, i2 ->
            reminderTime.text = "${strHour[hourNumberPicker.value]}:${strMin[minNumberPicker.value]} ${strAM[amNumberPicker.value]}"
        }

        minNumberPicker.setOnValueChangedListener { numberPicker, i, i2 ->
            reminderTime.text = "${strHour[hourNumberPicker.value]}:${strMin[minNumberPicker.value]} ${strAM[amNumberPicker.value]}"
        }

        amNumberPicker.setOnValueChangedListener { numberPicker, i, i2 ->
            reminderTime.text = "${strHour[hourNumberPicker.value]}:${strMin[minNumberPicker.value]} ${strAM[amNumberPicker.value]}"
        }

        getCurrentButton.setOnClickListener {
            hourData.value = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
            minData.value = Calendar.getInstance().get(Calendar.MINUTE)
            val amOrPm = if (hourData.value!! > 12){
                1
            } else {
                0
            }

            reminderTime.text = "${strHour[hourData.value!!%12-1]}:${minData.value} ${strAM[amOrPm]}"

            hourNumberPicker.value = strHour[hourData.value!!%12-1].toInt()-1
            minNumberPicker.value = (minData.value!!/5)
            amNumberPicker.value = amOrPm
        }

        okButtonTextView.setOnClickListener {
            Toast.makeText(this,reminderTime.text,Toast.LENGTH_SHORT).show()
        }
    }
}