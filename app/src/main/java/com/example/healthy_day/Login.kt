package com.example.healthy_day

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import com.example.healthy_day.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val firebaseAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // przenosze do rejestracji
        binding.textView.setOnClickListener {
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }
        //przenoszenie do zmiany hasła
        binding.textView2.setOnClickListener {
            val intent = Intent(this, Changepass::class.java)
            startActivity(intent)
        }

        // pobieranie danych
        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, Menu::class.java)
                        startActivity(intent)
                        Toast.makeText(this, "Zalogowano", Toast.LENGTH_SHORT).show()
                        firebaseAuth.sendPasswordResetEmail(email)
                    } else {
                        Toast.makeText(this, "Upss... coś poszło nie tak!!", Toast.LENGTH_SHORT).show()

                    }
                }
            } else {
                Toast.makeText(this, "Puste pola nie są dozwolone!!", Toast.LENGTH_SHORT).show()

            }
        }


    }



}