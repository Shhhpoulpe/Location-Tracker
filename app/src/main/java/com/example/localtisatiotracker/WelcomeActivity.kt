package com.example.localtisatiotracker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.localtisatiotracker.databinding.ActivityWelcomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class WelcomeActivity : AppCompatActivity() {

    var firebaseUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityWelcomeBinding.inflate(layoutInflater)


        binding.registerWelcomeBtn.setOnClickListener {
            val intent = Intent(this@WelcomeActivity,RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.loginWelcomeBtn.setOnClickListener {
            val intent = Intent(this@WelcomeActivity,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        firebaseUser = FirebaseAuth.getInstance().currentUser

        if(firebaseUser != null)
        {
            val intent = Intent(this@WelcomeActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}