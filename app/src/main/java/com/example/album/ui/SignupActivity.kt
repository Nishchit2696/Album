package com.example.album.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.album.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var user: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = FirebaseAuth.getInstance()

        binding.signup.setOnClickListener{
            registerUser()
        }


    }

    private fun registerUser(){
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()){

            user.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignupActivity()) {task ->

                    if (task.isSuccessful){
                        Toast.makeText(this, "User is registered", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this, "User is not registered", Toast.LENGTH_SHORT).show()
                    }
                }

        }else{
            Toast.makeText(this, "email and password cannot be empty", Toast.LENGTH_SHORT).show()
        }
    }
}