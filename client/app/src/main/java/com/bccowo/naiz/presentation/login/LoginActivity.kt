package com.bccowo.naiz.presentation.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bccowo.naiz.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
    }
}