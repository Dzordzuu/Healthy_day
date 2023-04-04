package com.example.healthy_day

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import com.example.healthy_day.databinding.ActivityPopScreenBinding
import com.example.healthy_day.databinding.ActivityProfilBinding
import com.example.healthy_day.databinding.ActivityWeightBinding
import kotlinx.android.synthetic.main.activity_pop_screen.*
import java.text.SimpleDateFormat
import java.util.*

class Pop_screen : AppCompatActivity() {
    private lateinit var binding: ActivityPopScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPopScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}