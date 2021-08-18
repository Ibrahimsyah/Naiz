package com.bccowo.naiz.presentation.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bccowo.naiz.databinding.ActivityLoginBinding
import com.bccowo.naiz.presentation.home.HomeActivity
import com.bccowo.naiz.presentation.register.RegisterActivity
import com.bccuwu.aic.core.config.SharedPreference

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = getSharedPreferences(SharedPreference.GLOBAL_PREF, Context.MODE_PRIVATE)
        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnForgotPassword.setOnClickListener {

        }

        binding.btnLogin.setOnClickListener {
            with(pref.edit()) {
                putBoolean(SharedPreference.PREF_LOGIN, true)
                apply()
            }

            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}