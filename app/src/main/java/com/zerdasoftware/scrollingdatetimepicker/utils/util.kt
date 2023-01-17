package com.zerdasoftware.scrollingdatetimepicker.utils

import androidx.lifecycle.MutableLiveData
import java.util.*

object Constants {

    val HOUR_PICKER_LIST = arrayOf(
        "00","01","02","03","04","05","06","07","08","09","10","11",
        "12","13","14","15","16","17","18","19","20","21","22","23")

    val MIN_PICKER_LIST = arrayOf("00","15","30","45")

    val START_HOUR_TIME = MutableLiveData<Int>()
    val START_MIN_TIME = MutableLiveData<Int>()

    val END_HOUR_TIME = MutableLiveData<Int>()
    val END_MIN_TIME = MutableLiveData<Int>()

    val PV1 = MutableLiveData<Int>()
    val PV2 = MutableLiveData<Int>()

    val IS_START_SELECTED = MutableLiveData<Boolean>()

    val HOUR = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    val MIN = Calendar.getInstance().get(Calendar.MINUTE)

    const val SET_VALUE = 15
    const val START_VISIT_HOUR = 9










}