package com.example.localtisatiotracker

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.localtisatiotracker.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var refUsers: DatabaseReference
    private var firebaseUserID: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)

        auth = FirebaseAuth.getInstance()

        binding.buttonSignIn.setOnClickListener {
            loginUser()
        }

        setContentView(binding.root)
    }

    private fun loginUser()
    {
        var email: String? = null
        email = findViewById<EditText>(R.id.email_login).text.toString()
        var pass: String? = null
        pass = findViewById<EditText>(R.id.password_login).text.toString()

        when {
            email == "" -> {
                Toast.makeText(this@LoginActivity, "Write your Email.", Toast.LENGTH_LONG).show()
            }
            pass == "" -> {
                Toast.makeText(this@LoginActivity, "Write your password.", Toast.LENGTH_LONG).show()

            }
            else ->
            {
                auth.signInWithEmailAndPassword(email,pass)
                    .addOnCompleteListener {task ->
                        if (task.isSuccessful)
                        {
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(this@LoginActivity, "Error Message" + task.exception!!.message.toString(),Toast.LENGTH_LONG).show()
                        }
                        }
            }
        }
    }
}
