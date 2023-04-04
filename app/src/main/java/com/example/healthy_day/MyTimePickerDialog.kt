package com.example.healthy_day

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.DialogFragment
import java.util.*

class MyTimePickerDialog:DialogFragment() {

    private val c = Calendar.getInstance()
    private val hour = c.get(Calendar.HOUR_OF_DAY)
    private val minute = c.get(Calendar.MINUTE)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return TimePickerDialog(requireActivity(),activity as TimePickerDialog.OnTimeSetListener,hour,minute,DateFormat.is24HourFormat(activity))

    }
}