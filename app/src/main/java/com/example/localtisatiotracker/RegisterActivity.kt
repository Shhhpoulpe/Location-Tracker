package com.example.localtisatiotracker

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.localtisatiotracker.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity: AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var refUsers: DatabaseReference
    private var firebaseUserID: String = ""
    /*private var mail:String? = null
    private var ass:String? = null*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRegisterBinding.inflate(layoutInflater)

        auth = FirebaseAuth.getInstance()

        binding.buttonSignUp.setOnClickListener {
            registerUser()
        }

        setContentView(binding.root)

    }

    private fun registerUser() {

        var email: String? = null
        email = findViewById<EditText>(R.id.email_register).text.toString()
        var pass: String? = null
        pass = findViewById<EditText>(R.id.password_register).text.toString()

        when {
            email == "" -> {
                Toast.makeText(this@RegisterActivity, "Write your Email.",Toast.LENGTH_LONG).show()
            }
            pass == "" -> {
                Toast.makeText(this@RegisterActivity, "Write your password.",Toast.LENGTH_LONG).show()

            }
            else -> {
                auth.createUserWithEmailAndPassword(email,pass)
                    .addOnCompleteListener{ task -> if (task.isSuccessful) {
                        firebaseUserID = auth.currentUser!!.uid
                        refUsers = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUserID)

                        val userHashMap = HashMap<String, Any>()
                        userHashMap["uid"] = firebaseUserID

                        refUsers.updateChildren(userHashMap)
                                .addOnCompleteListener {
                                    if (task.isSuccessful) {
                                        val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                                        startActivity(intent)
                                        finish()
                                    }
                                }
                    } else {
                        Toast.makeText(this@RegisterActivity, "Error Message" + task.exception!!.message.toString(),Toast.LENGTH_LONG).show()
                    }
                    }
            }
        }
    }
}