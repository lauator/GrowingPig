package com.example.growingpig.view.ui.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.example.growingpig.R
import com.example.growingpig.databinding.ActivitySignInBinding


private lateinit var binding: ActivitySignInBinding

private lateinit var etMail: EditText
private lateinit var etPassword: EditText
private lateinit var cb_keepConnect: CheckBox


class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        etMail = binding.etEmail
        etPassword = binding.etPassword
        cb_keepConnect = binding.cbKeepConnect

        val btnLogin = binding.btnLogin
        val tvRegister = binding.tvRegister

        btnLogin.setOnClickListener()
        {
            onClickSignIn()
        }

        tvRegister.setOnClickListener()
        {
            onClickSignUp()
        }



    }

    private fun onClickSignIn() {

        val email = etMail.text.toString()
        val password = etPassword.text.toString()

        if(!userInputIsValid(email, password))
        {
            return
        }

        loginUser(email, password)


    }

    private fun userInputIsValid(email: String, password: String): Boolean {
        var inputIsValid = true

        if(email.isEmpty() || password.isEmpty())
        {
            inputIsValid = false
            showToast("Debes completar TODOS los campos")
        }
        else if(!email.contains("@"))
        {
            inputIsValid = false
            showToast("Completa con un correo valido")
        }

        return inputIsValid

    }

    private fun loginUser(email: String, password: String) {

        saveUserData(email, password)

        val loginIntent = Intent(this, MainActivity::class.java)
        startActivity(loginIntent)
        finish()

    }

    private fun saveUserData(email: String, password: String) {
        if(cb_keepConnect.isChecked)
        {
            val prefs = getSharedPreferences(SignUpActivity.SHARED_PREFS_NAME, MODE_PRIVATE)
            val editor = prefs.edit()

            editor.putString(SignUpActivity.KEY_SHARED_PREFS_EMAIL, email)
            editor.putString(SignUpActivity.KEY_SHARED_PREFS_PASSWORD, password)

            editor.commit()
        }

    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun onClickSignUp() {
        var registerIntent = Intent(this, SignUpActivity::class.java)
        startActivity(registerIntent)
        finish()
    }
}