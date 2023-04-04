package com.example.healthy_day

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import com.example.healthy_day.databinding.ActivityRegistrationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Registration : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var firebaseAuth: FirebaseAuth
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.textView.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)


        }
        binding.button.setOnClickListener {
            val nick = binding.nickEt.text.toString()
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()
            val confirmPass = binding.confirmPassEt.text.toString()





            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {

                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        //Pobieranie id z rejestracji do clouda
                        firebaseAuth.currentUser?.sendEmailVerification()
                            ?.addOnSuccessListener {
                            Toast.makeText(this, "Proszę potwierdzić email", Toast.LENGTH_LONG).show()
                        }
                            ?.addOnFailureListener{
                                Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
                            }
                        val currentUser = FirebaseAuth.getInstance().currentUser!!.uid
                        //val currentUser = firebaseAuth.currentUser
                        if (it.isSuccessful) {


                            val user = hashMapOf(
                                "nick" to nick,
                                "wiek" to "",
                                "wzrost" to "",
                                "id" to currentUser,
                                "aktualna_masa" to "",
                                "docelowa_masa" to "",
                                "aktywność" to "",
                                "płeć" to "",
                                "kcal" to "",
                                "bmi" to "",
                                "odp" to ""
                            )
                            val weight = hashMapOf(
                                "waga" to "",
                                "data" to ""
                            )
                            db.collection("users")
                                //.add(db.collection("profile")
                                .document(currentUser)
                                .set(user)






                            val intent = Intent(this, Menu::class.java)
                            Toast.makeText(this, "Zarejestrowano pomyślnie", Toast.LENGTH_LONG).show()
                            startActivity(intent)

                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                        }
                    }
                } else {
                    Toast.makeText(this, "Hasła są różne", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Puste pola nie są dozwolone", Toast.LENGTH_SHORT).show()

            }


        }


    }

}