package com.example.healthy_day

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_pop_screen.view.*
import kotlinx.android.synthetic.main.activity_weight.*

import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthy_day.databinding.ActivityLoginBinding
import com.example.healthy_day.databinding.ActivityWeightBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

import java.security.KeyStore.Entry
import kotlin.math.roundToInt

//import com.anychart.AnyChart
//import com.anychart.AnyChartView
lateinit var barChart: BarChart

class Weight : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var db: FirebaseFirestore

    lateinit var binding: ActivityWeightBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWeightBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()


        //is user is alreay Login
        if (auth.currentUser == null) {
            goToLogin()
        }
        var currentUser = auth.currentUser

        loadAllData(currentUser!!.uid.toString())
//dodawanie

        binding.buttonPop.setOnClickListener {
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.activity_pop_screen, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("Dodaj wage")
            val mAlertDialog = mBuilder.show()
            mAlertDialog.getWindow()!!.setLayout(1000, 1000);

            mDialogView.button_zap.setOnClickListener {
                //dismiss dialog
                mAlertDialog.dismiss()

                //var task = binding.etTask.text.toString().trim()
                val waga = mDialogView.emailEt.text.toString()
                val data = mDialogView.passET.text.toString()

                val taskData = TaskModel(waga,data, false, currentUser!!.uid.toString())
                db.collection("weight")
                    .add(taskData)
                    .addOnSuccessListener {
                        Toast.makeText(this@Weight, "Zapisano", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this@Weight, "Nie Zapisano", Toast.LENGTH_SHORT).show()
                        Log.e("HA", "Error saving : Err :" + it.message)
                    }
            }

        }

        //swipe refresh
        binding.refresh.setOnRefreshListener {
            if (binding.refresh.isRefreshing) {
                binding.refresh.isRefreshing = false
            }
            loadAllData(currentUser!!.uid)
        }

    }


    fun goToLogin() {
        startActivity(Intent(this@Weight, MainActivity::class.java))
        finish()
    }

    //for laoding all task from server
    fun loadAllData(userID: String) {

        val taksList = ArrayList<TaskModel>()

        var ref = db.collection("weight")
        ref.whereEqualTo("userID", userID)
            .get()
            .addOnSuccessListener {
                if (it.isEmpty) {
                    Toast.makeText(this@Weight, "Brak Danych", Toast.LENGTH_SHORT).show()
                    return@addOnSuccessListener
                }
                for (doc in it) {
                    val taskModel = doc.toObject(TaskModel::class.java)
                    taksList.add(taskModel)
                }

                binding.rvToDoList.apply {
                    layoutManager =
                        LinearLayoutManager(this@Weight, RecyclerView.VERTICAL, false)
                    adapter = TaskAdapter(taksList, this@Weight)
                }

            }
    }

}

