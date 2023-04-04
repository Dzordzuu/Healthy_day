package com.example.healthy_day

import android.content.ContentValues.TAG
import android.graphics.Color
import android.graphics.Insets.add
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.Insets.add
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthy_day.databinding.ActivityWeightBinding
import com.example.healthy_day.databinding.ActivityWykresBinding
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_wykres.*



class Wykres : AppCompatActivity() {

    lateinit var linelist: ArrayList<Entry>
    lateinit var lineDataSet: LineDataSet
    lateinit var lineData: LineData
    lateinit var auth: FirebaseAuth
    lateinit var db: FirebaseFirestore

    lateinit var binding: ActivityWykresBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWykresBinding.inflate(layoutInflater)
        setContentView(binding.root)
        linelist= ArrayList()
        val taksList = ArrayList<Any>()
        val currentuser = FirebaseAuth.getInstance().currentUser!!.uid

        //linelist.add(Entry(0f,77f))


        for (i in 0 until 10) {

            val value = (Math.random() * 180f).toFloat() - 30
            linelist.add(Entry(i.toFloat(), value/*, resources.getDrawable(R.drawable.star)*/))
        }
        lineDataSet = LineDataSet(linelist,"Zmiana wagi")

        lineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        lineData = LineData(lineDataSet)
        line_chart.data =lineData
        //lineDataSet.setColors(*ColorTemplate.JOYFUL_COLORS)


        lineDataSet.valueTextColor = Color.BLACK
        lineDataSet.valueTextSize = 10f
        lineDataSet.setDrawFilled(true)
        lineDataSet.setDrawValues(true)



    }








}

