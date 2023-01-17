package com.zerdasoftware.scrollingdatetimepicker.ui.fragment

import android.os.Bundle
import android.view.View
import com.zerdasoftware.scrollingdatetimepicker.Interface.IsStartDate
import com.zerdasoftware.scrollingdatetimepicker.Interface.SetPickerData
import com.zerdasoftware.scrollingdatetimepicker.R
import com.zerdasoftware.scrollingdatetimepicker.databinding.FragmentPickerBoardBinding
import com.zerdasoftware.scrollingdatetimepicker.ui.base.BaseFragment
import com.zerdasoftware.scrollingdatetimepicker.utils.Constants.END_HOUR_TIME
import com.zerdasoftware.scrollingdatetimepicker.utils.Constants.PV1
import com.zerdasoftware.scrollingdatetimepicker.utils.Constants.PV2
import com.zerdasoftware.scrollingdatetimepicker.utils.Constants.HOUR_PICKER_LIST
import com.zerdasoftware.scrollingdatetimepicker.utils.Constants.IS_START_SELECTED
import com.zerdasoftware.scrollingdatetimepicker.utils.Constants.MIN_PICKER_LIST
import com.zerdasoftware.scrollingdatetimepicker.utils.Constants.START_VISIT_HOUR
import kotlinx.android.synthetic.main.fragment_picker_board.*
import zerda.software.HelloWorld

class PickerBoardFragment :BaseFragment<FragmentPickerBoardBinding>(),SetPickerData, IsStartDate {
    override val layoutId: Int = R.layout.fragment_picker_board


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val helloworld = HelloWorld("Cihan")

        println(helloworld.sayHello())

        pickerSetData()
        pickerValueChanged()

        binding.okButtonTextView.setOnClickListener {}
    }

    private fun pickerSetData() {
        PV1.observe(viewLifecycleOwner) {
            binding.hourNumberPicker.value = it
        }
        PV2.observe(viewLifecycleOwner) {
            binding.minNumberPicker.value = it
        }

        binding.hourNumberPicker.minValue = 0
        binding.hourNumberPicker.maxValue = HOUR_PICKER_LIST.size-1
        binding.hourNumberPicker.displayedValues = HOUR_PICKER_LIST


        binding.minNumberPicker.minValue = 0
        binding.minNumberPicker.maxValue = MIN_PICKER_LIST.size-1
        binding.minNumberPicker.displayedValues = MIN_PICKER_LIST
    }

    private fun pickerValueChanged() {
        binding.hourNumberPicker.setOnValueChangedListener { numberPicker, i, i2 ->
            setPickerData()
        }

        binding.minNumberPicker.setOnValueChangedListener { numberPicker, i, i2 ->
            setPickerData()
        }
    }

    private fun setPickerData() {
        val setTimeDate =HomeFragment()
        if (IS_START_SELECTED.value!!){
            if (hourNumberPicker.value>=START_VISIT_HOUR){
                setTimeDate.setPickerTimeDate(hourNumberPicker.value,minNumberPicker.value,IS_START_SELECTED.value!!)
            } else {
                hourNumberPicker.value = START_VISIT_HOUR
                setTimeDate.setPickerTimeDate(hourNumberPicker.value,minNumberPicker.value,IS_START_SELECTED.value!!)
            }
        } else {
            if (hourNumberPicker.value>=END_HOUR_TIME.value!!){
                setTimeDate.setPickerTimeDate(hourNumberPicker.value,minNumberPicker.value,IS_START_SELECTED.value!!)
            } else{
                hourNumberPicker.value = END_HOUR_TIME.value!!
                setTimeDate.setPickerTimeDate(hourNumberPicker.value,minNumberPicker.value,IS_START_SELECTED.value!!)
            }
        }




    }

    override fun setPickersValue(v1: Int, v2: Int) {
        PV1.value = v1
        PV2.value = v2
    }

    override fun selectedStartDate(isStart: Boolean) {
        IS_START_SELECTED.value = isStart
    }

}