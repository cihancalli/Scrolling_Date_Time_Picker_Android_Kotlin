package com.zerdasoftware.scrollingdatetimepicker.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.zerdasoftware.scrollingdatetimepicker.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(){

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*


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
         */
    }
}