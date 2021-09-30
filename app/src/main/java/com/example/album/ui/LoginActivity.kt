package com.example.album.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.album.R
import com.example.album.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth;

    private lateinit var binding: ActivityLoginBinding

    private lateinit var tvRegisterRedirect: TextView
    private lateinit var login: Button
    private lateinit var email: EditText
    private lateinit var password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance();

        tvRegisterRedirect= findViewById(R.id.tvRegisterRedirect)
        login = findViewById(R.id.login)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)

        tvRegisterRedirect.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }

        login.setOnClickListener{

            if (email.text.trim().toString().isNotEmpty()|| password.text.trim().toString().isNotEmpty()){

                signInUser(email.text.trim().toString(),password.text.trim().toString());
            }else{
                Toast.makeText(this,"Inout required",Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun signInUser(email:String,password:String){

        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener (this){ task ->
                if(task.isSuccessful){

                    val intent = Intent(this, NavbarActivity::class.java);
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Error  !!"+task.exception, Toast.LENGTH_SHORT).show()
                }
            }
    }
}