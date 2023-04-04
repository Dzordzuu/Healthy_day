package com.example.healthy_day

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import com.example.healthy_day.databinding.ActivityProfilBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.widget.Toast
import com.example.healthy_day.databinding.ActivityLoginBinding
import com.example.healthy_day.databinding.ActivityRegistrationBinding
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.math.roundToInt


class Profil : AppCompatActivity() {
    val db = Firebase.firestore
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityProfilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Drop card
        val feelings = resources.getStringArray(R.array.feelings)
        val arrayAdapter = ArrayAdapter(this, R.layout.drop_down, feelings)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)

        val plec = resources.getStringArray(R.array.plec)
        val arrayplec = ArrayAdapter(this, R.layout.plec_drop, plec)
        binding.plec.setAdapter(arrayplec)

////Zapis do bazy(update)




        val currentuser = FirebaseAuth.getInstance().currentUser!!.uid
        binding.buttonSave.setOnClickListener {
            val wiek = binding.inputWiek.text.toString()
            val wzrost = binding.inputWzrost.text.toString()
            val masaakt = binding.inputMasaakt.text.toString()
            val masadoc = binding.inputMasadocs.text.toString()
            val akt = binding.autoCompleteTextView.text.toString()
            val plec = binding.plec.text.toString()
            val Intent = Intent(this, Menu::class.java)
            startActivity(Intent)

// Error

            if (wiek.isEmpty() || wzrost.isEmpty() || (masaakt.isEmpty()) || (masadoc.isEmpty()) || (akt.isEmpty()) ) {
                val intent = Intent(this, Profil::class.java)
                startActivity(intent)
                Toast.makeText(this, "Pola nie mogą być puste", Toast.LENGTH_SHORT).show()
            }
            else {


                // Create a new user with a first and last name

                val user = hashMapOf(
                    "wiek" to wiek,
                    "wzrost" to wzrost,
                    "aktualna_masa" to masaakt,
                    "docelowa_masa" to masadoc,
                    "aktywność" to akt,
                    "płeć" to plec,
                    "kcal" to "",
                    "bmi" to "",
                    "odp" to ""
                )
                val wiekk = hashMapOf(
                    "wiek" to wiek
                )
                db.collection("users")
                    .document(currentuser)
                    .update(user as Map<String, String>)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Zapisano", Toast.LENGTH_SHORT)
                            .show()
                        //Odczytywanie
                        db.collection("users")
                            .get()
                            .addOnSuccessListener {
                                val odwiek = it.documents.get(0).data?.get("wiek").toString()
                                val odwzrost = it.documents.get(0).data?.get("wzrost").toString()
                                val odakt =
                                    it.documents.get(0).data?.get("aktualna_masa").toString()
                                val oddoc =
                                    it.documents.get(0).data?.get("docelowa_masa").toString()
                                val odaktywnosc =
                                    it.documents.get(0).data?.get("aktywność").toString()
                                val odplec = it.documents.get(0).data?.get("płeć").toString()

                                val Wiek = odwiek.toInt()
                                val Wzrost = odwzrost.toInt()
                                val Akt = odakt.toInt()
                                val Doc = oddoc.toInt()
                                var akt = 0.0


                                if (odaktywnosc == "Niski") {
                                    akt = 1.3.toDouble()

                                }
                                if (odaktywnosc == "Umiarkowany") {
                                    akt = 1.5.toDouble()

                                }
                                if (odaktywnosc == "Aktywny") {
                                    akt = 1.75.toDouble()

                                }
                                if (odaktywnosc == "Bardzo aktywny") {
                                    akt = 2.0.toDouble()

                                }
                                if (odplec == "Mężczyzna") {
                                    val Wynikm =
                                        ((66.47 + (13.75 * Akt) + (5 * Wzrost) - (6.75 * Wiek)) * akt)
                                    val Bmi = Akt / ((Wzrost * 0.01) * (Wzrost * 0.01))
                                    var odp = ""
                                    if(Bmi<18.5)
                                    {
                                        odp = "Niedowaga"
                                    }
                                    if((18.5<=Bmi) && (Bmi<24.9))
                                    {
                                        odp = "Prawidłowa"
                                    }
                                    if((25<Bmi) && (Bmi<29.9))
                                    {
                                        odp = "Nadwaga"
                                    }
                                    if((30<Bmi) && (Bmi<34.9))
                                    {
                                        odp = "Otyłość"
                                    }
                                    if(Bmi>35)
                                    {
                                        odp = "Otyłość skrajna"
                                    }
                                    db.collection("users")
                                        .document(currentuser)
                                        .update("kcal", Wynikm.roundToInt())

                                    db.collection("users")
                                        .document(currentuser)
                                        .update("bmi", Bmi.roundToInt())
                                    db.collection("users")
                                        .document(currentuser)
                                        .update("odp", odp)

                                }
                                if (odplec == "Kobieta") {
                                    val Wynikk = ((655.09 + (9.56 * Akt) + (1.85 * Wzrost) - (4.67 * Wiek)) * akt)

                                    val Bmi = Akt / ((Wzrost * 0.01) * (Wzrost * 0.01))
                                    var odp = ""
                                    if(Bmi<18.5)
                                    {
                                        odp = "Niedowaga"
                                    }
                                    if((18.5<=Bmi) && (Bmi<24.9))
                                    {
                                        odp = "Prawidłowa"
                                    }
                                    if((25<Bmi) && (Bmi<29.9))
                                    {
                                        odp = "Nadwaga"
                                    }
                                    if((30<Bmi) && (Bmi<34.9))
                                    {
                                        odp = "Otyłość"
                                    }
                                    if(Bmi>35)
                                    {
                                        odp = "Otyłość skrajna"
                                    }
                                    db.collection("users")
                                        .document(currentuser)
                                        .update("kcal", Wynikk.roundToInt())
                                    db.collection("users")
                                        .document(currentuser)
                                        .update("bmi", Bmi.roundToInt())
                                    db.collection("users")
                                        .document(currentuser)
                                        .update("odp", odp)

                                }




                            }
                            .addOnFailureListener {
                                it.printStackTrace()
                                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                            }
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Coś poszło nie tak", Toast.LENGTH_SHORT).show()
                    }
            }

        }



    }

}
