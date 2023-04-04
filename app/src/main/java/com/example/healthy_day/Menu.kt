package com.example.healthy_day

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.healthy_day.databinding.ActivityMenuBinding
import com.example.healthy_day.databinding.ActivityProfilBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class Menu : AppCompatActivity() {
    val db = Firebase.firestore
    lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityMenuBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        val login = findViewById<CardView>(R.id.card_logout)
        login.setOnClickListener {
            Toast.makeText(this, "Wylogowano", Toast.LENGTH_SHORT).show()
            firebaseAuth.signOut()
            goToLogin()


        }
        val cardstat = findViewById<CardView>(R.id.statystyka)
        cardstat.setOnClickListener {
            val Intent = Intent(this, Wykres::class.java)
            startActivity(Intent)
        }
        val cardkcal = findViewById<CardView>(R.id.cardkcal)
        cardkcal.setOnClickListener {
            val Intent = Intent(this, Menu::class.java)
            startActivity(Intent)
        }
        val profil = findViewById<CardView>(R.id.profil)
        profil.setOnClickListener {
            val Intent = Intent(this, Profil::class.java)
            startActivity(Intent)
        }
        val car_weight = findViewById<CardView>(R.id.card_weight)
        car_weight.setOnClickListener {
            val Intent = Intent(this, Weight::class.java)
            startActivity(Intent)
        }
        val card_alarm = findViewById<CardView>(R.id.card_alarm)
        card_alarm.setOnClickListener {
            val Intent = Intent(this, Alarm::class.java)
            startActivity(Intent)
        }
        val currentuser = FirebaseAuth.getInstance().currentUser!!.uid
        val qwert = db.collection("users")
        qwert.document(currentuser).get().addOnCompleteListener() { task ->
            val document = task.result
            val kcal = document.get("kcal").toString()
            val nick = document.get("nick").toString()
            val bmi = document.get("bmi").toString()
            val odp = document.get("odp").toString()
            binding.kcal.setText(kcal)
            binding.bmi.setText(bmi)
            binding.Bmi.setText(odp)
            binding.textView.setText("Witaj" + " " + nick   )

           //val Bmi = bmi.toInt()
            //val Bmi = document.get("bmi")

        }
        /*
        val user = firebaseAuth.currentUser

        db.collection("users")

            .get()
            .addOnSuccessListener {
                val odwiek = it.documents.get(0).data?.get("wiek").toString()
                binding.kcal.setText(odwiek)

            }
            .addOnFailureListener{
                it.printStackTrace()
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                val Intent = Intent(this, Profil::class.java)
                startActivity(Intent)
            }
*/

    }

     fun goToLogin() {
         startActivity(Intent(this@Menu, MainActivity::class.java))
         finish()
    }

}
