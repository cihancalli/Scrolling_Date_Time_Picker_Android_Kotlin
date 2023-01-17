package com.zerdasoftware.scrollingdatetimepicker.ui.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.zerdasoftware.scrollingdatetimepicker.Interface.SetTimeDate
import com.zerdasoftware.scrollingdatetimepicker.R
import com.zerdasoftware.scrollingdatetimepicker.databinding.FragmentHomeBinding
import com.zerdasoftware.scrollingdatetimepicker.ui.base.BaseFragment
import com.zerdasoftware.scrollingdatetimepicker.utils.Constants.END_HOUR_TIME
import com.zerdasoftware.scrollingdatetimepicker.utils.Constants.END_MIN_TIME
import com.zerdasoftware.scrollingdatetimepicker.utils.Constants.HOUR_PICKER_LIST
import com.zerdasoftware.scrollingdatetimepicker.utils.Constants.IS_START_SELECTED
import com.zerdasoftware.scrollingdatetimepicker.utils.Constants.MIN_PICKER_LIST
import com.zerdasoftware.scrollingdatetimepicker.utils.Constants.START_HOUR_TIME
import com.zerdasoftware.scrollingdatetimepicker.utils.Constants.START_MIN_TIME


class HomeFragment : BaseFragment<FragmentHomeBinding>(),SetTimeDate {
    override val layoutId: Int = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDefaultData()
        setupButton()
    }

    @SuppressLint("SetTextI18n")
    private fun setDefaultData() {
        IS_START_SELECTED.value = true
        setIsAmSelected(true)
        setTextColor("#000000","#A3A7AA")

        START_HOUR_TIME.observe(viewLifecycleOwner){ hour->
            START_MIN_TIME.observe(viewLifecycleOwner){ min->
                binding.startHourTextView.text = "${HOUR_PICKER_LIST[hour]}:${MIN_PICKER_LIST[min]}"
                END_HOUR_TIME.value = hour + ((min+1)/4)
                END_MIN_TIME.value = (min+1)%4
            }
        }

        END_HOUR_TIME.observe(viewLifecycleOwner){ hour->
            END_MIN_TIME.observe(viewLifecycleOwner){ min->
                binding.endHourTextView.text = "${HOUR_PICKER_LIST[hour]}:${MIN_PICKER_LIST[min]}"
            }
        }
    }

    private fun setupButton() {
        binding.startHourTextView.setOnClickListener {
            setIsAmSelected(true)
            setTextColor("#000000","#A3A7AA")
        }

        binding.endHourTextView.setOnClickListener {
            setIsAmSelected(false)
            setTextColor("#A3A7AA","#000000")
            START_HOUR_TIME.observe(viewLifecycleOwner){ hour->
                START_MIN_TIME.observe(viewLifecycleOwner){ min->
                    binding.endHourTextView.text = "${HOUR_PICKER_LIST[hour]}:${MIN_PICKER_LIST[(min+1)%4]}"
                }
            }

        }
    }

    private fun setTextColor(s: String, s1: String) {
        binding.startHourTextView.setTextColor(Color.parseColor(s))
        binding.startTitleTextView.setTextColor(Color.parseColor(s))

        binding.endHourTextView.setTextColor(Color.parseColor(s1))
        binding.endTitleTextView.setTextColor(Color.parseColor(s1))
    }

    private fun setIsAmSelected(b: Boolean) {
        val selectedAm = PickerBoardFragment()
        selectedAm.selectedStartDate(b)
    }

    override fun setPickerTimeDate(hour:Int,min:Int,isStart:Boolean) {
        if (isStart){
            START_HOUR_TIME.value = hour
            START_MIN_TIME.value = min
        } else {
            END_HOUR_TIME.value = hour
            END_MIN_TIME.value = min
        }
    }


}