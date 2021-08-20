package com.bccowo.naiz.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bccowo.naiz.R
import com.bccowo.naiz.core.data.source.remote.request.LoginRequest
import com.bccowo.naiz.databinding.ActivityLoginBinding
import com.bccowo.naiz.presentation.home.HomeActivity
import com.bccowo.naiz.presentation.register.RegisterActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnForgotPassword.setOnClickListener {

        }

        binding.btnLogin.setOnClickListener { loginUser() }

        loginViewModel.loading.observe(this, {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        loginViewModel.status.observe(this, { status ->
            status?.let {
                if (it) {
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, getString(R.string.auth_error), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun loginUser() {
        val email = binding.edEmail.text.toString()
        val password = binding.edPassword.text.toString()
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, R.string.make_sure_all_form_filled, Toast.LENGTH_SHORT).show()
            return
        }
        val loginRequest = LoginRequest(email, password)
        loginViewModel.loginUser(loginRequest)
    }
}