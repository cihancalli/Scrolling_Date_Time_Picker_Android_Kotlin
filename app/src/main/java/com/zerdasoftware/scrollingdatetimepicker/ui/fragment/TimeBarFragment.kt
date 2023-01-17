package com.zerdasoftware.scrollingdatetimepicker.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.zerdasoftware.scrollingdatetimepicker.Interface.IsStartDate
import com.zerdasoftware.scrollingdatetimepicker.R
import com.zerdasoftware.scrollingdatetimepicker.databinding.FragmentTimeBarBinding
import com.zerdasoftware.scrollingdatetimepicker.ui.base.BaseFragment
import com.zerdasoftware.scrollingdatetimepicker.utils.Constants.HOUR
import com.zerdasoftware.scrollingdatetimepicker.utils.Constants.HOUR_PICKER_LIST
import com.zerdasoftware.scrollingdatetimepicker.utils.Constants.IS_START_SELECTED
import com.zerdasoftware.scrollingdatetimepicker.utils.Constants.MIN
import com.zerdasoftware.scrollingdatetimepicker.utils.Constants.SET_VALUE
import kotlinx.android.synthetic.main.fragment_picker_board.*
import java.util.*

class TimeBarFragment : BaseFragment<FragmentTimeBarBinding>(), IsStartDate {
    override val layoutId: Int = R.layout.fragment_time_bar
    private val setPickers = PickerBoardFragment()
    private var p1 = 9
    private var p2 = 0
    private var min = MIN
    private var hour = HOUR

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDefaultValue()
        setupButton()
    }

    @SuppressLint("SetTextI18n")
    private fun setDefaultValue() {
       setPValue(HOUR, MIN)
        binding.timeBarCurrentTimeTextView.text = "${HOUR_PICKER_LIST[HOUR]}:$MIN"
        binding.timeBarMinusOneTextView.text = "-${SET_VALUE}m"
        binding.timeBarPlusOneTextView.text = "+${SET_VALUE}m"
        setPickers.setPickersValue(p1, p2)
    }

    private fun setPValue(h:Int,m:Int) {
        p1 = h + (((m/15)+1)/4)
        p2 = ((m/15)+1)%4
    }

    private fun setupButton() {
        binding.timeBarMinusOneTextView.setOnClickListener {
            min-= SET_VALUE
            if (min<0 ){
                hour -= 1
                min += 60
            }
            setPValue(hour,min)
            setPickerDate()
        }
        binding.timeBarCurrentTimeTextView.setOnClickListener {
            setPValue(HOUR, MIN)
            setPickerDate()
            min = MIN
            hour = HOUR
        }
        binding.timeBarPlusOneTextView.setOnClickListener {
            min+= SET_VALUE
            if (min>=60){
                hour +=min/60
                min -=60
            }
            setPValue(hour,min)
            setPickerDate()
        }
    }

    private fun setPickerDate() {
        val setTimeDate =HomeFragment()
        setTimeDate.setPickerTimeDate(p1,p2,IS_START_SELECTED.value!!)
        setPickers.setPickersValue(p1, p2)
    }

    override fun selectedStartDate(isStart: Boolean) {
        IS_START_SELECTED.value = isStart
    }
}