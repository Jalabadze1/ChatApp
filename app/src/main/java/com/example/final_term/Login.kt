package com.example.final_term

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var pass: EditText
    private lateinit var log_btn: Button
    private lateinit var sign_btn: Button

    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        email = findViewById(R.id.mail)
        pass = findViewById(R.id.pass)
        log_btn = findViewById(R.id.log_btn)
        sign_btn = findViewById(R.id.sign_btn)

        sign_btn.setOnClickListener {
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }

        log_btn.setOnClickListener {
            val email = email.text.toString()
            val password = pass.text.toString()

            logIn(email, password)

        }
    }

        private fun logIn(email: String, password: String){
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        val intent = Intent(this@Login, MainActivity::class.java)
                        finish()
                        startActivity(intent)



                    } else {
                        Toast.makeText(this@Login, "მომხმარებელი არ არსებობს", Toast.LENGTH_SHORT).show()
                    }
                }
        }



}