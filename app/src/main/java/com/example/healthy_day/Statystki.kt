package com.example.healthy_day

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_wykres.*
import java.text.FieldPosition
import java.text.Format
import java.text.ParsePosition
import java.util.*

class Statystki : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statystki)
    }
}