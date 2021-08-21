package com.bccowo.naiz.presentation.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bccowo.naiz.R
import com.bccowo.naiz.core.data.source.remote.request.RegisterRequest
import com.bccowo.naiz.databinding.ActivityRegisterBinding
import com.bccowo.naiz.presentation.login.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel: RegisterViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerViewModel.loading.observe(this, {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        registerViewModel.status.observe(this, {
            if (it) {
                Toast.makeText(this, getString(R.string.register_success), Toast.LENGTH_SHORT)
                    .show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        })

        binding.btnRegister.setOnClickListener { registerUser() }
    }

    private fun registerUser() {
        val name = binding.edFullname.text.toString()
        val phone = binding.edPhoneNumber.text.toString()
        val email = binding.edEmail.text.toString()
        val password = binding.edPassword.text.toString()
        val confPassword = binding.edConfirmPassword.text.toString()

        if (name.isBlank() || phone.isBlank() || email.isBlank() || password.isBlank() || confPassword.isBlank()) {
            Toast.makeText(this, getString(R.string.make_sure_all_form_filled), Toast.LENGTH_SHORT)
                .show()
            return
        }

        if (password != confPassword) {
            Toast.makeText(
                this,
                getString(R.string.make_sure_confirmation_password_matched),
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val registerRequest = RegisterRequest(name, email, phone, password)
        registerViewModel.registerUser(registerRequest)
    }
}