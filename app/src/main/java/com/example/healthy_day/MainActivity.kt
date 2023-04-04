package com.example.healthy_day

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private val fbAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val login = findViewById<Button>(R.id.button_log)
        login.setOnClickListener {
            val Intent = Intent(this, Login::class.java)
            startActivity(Intent)
        }
        val registration = findViewById<Button>(R.id.button_reg)
        registration.setOnClickListener {
            val Intent = Intent(this, Registration::class.java)
            startActivity(Intent)

        }
    }

}