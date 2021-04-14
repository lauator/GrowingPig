package com.example.growingpig.view.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.growingpig.databinding.ActivitySignUpBinding


class SignUpActivity : AppCompatActivity() {
    /*
     TODO  En principio voy a hacer un Shared Preferences para guardar los datos del usuario, a pesar de que se
     de que no es la mejor forma de guardar datos sensibles, pero como la app no guarda informacion tan importante
     es tolerable
     */

    val SHARED_PREFS_NAME = "PREFS"

    val KEY_SHARED_PREFS_EMAIL = "KEY_SHARED_PREFS_EMAIL"
    val KEY_SHARED_PREFS_PASSWORD = "KEY_SHARED_PREFS_PASSWORD"

    val EXTRA_NAME = "EXTRA_NAME"
    val EXTRA_EMAIL = "EXTRA_EMAIL"

    private lateinit var binding: ActivitySignUpBinding

    private lateinit var etUser: EditText
    private lateinit var etMail: EditText
    private lateinit var etPassword: EditText




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        etUser = binding.etUser
        etMail = binding.etEmail
        etPassword = binding.etPassword
        val btnSignUp = binding.btnSignUp
        val tvSignIn = binding.tvSignIn


        btnSignUp.setOnClickListener()
        {
            onClickSignUp()
        }

        tvSignIn.setOnClickListener()
        {
            onClickSignIn()
        }

        verifyUserSavedCredentials()

    }

    private fun verifyUserSavedCredentials() {

        val prefs = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE)

        val email = prefs.getString(KEY_SHARED_PREFS_EMAIL, null)
        val password = prefs.getString(KEY_SHARED_PREFS_PASSWORD, null)

        if (email == null && password == null)
        {
            return
        }

        registerUser("", email!!, password!!)
    }

    private fun onClickSignUp() {

        var user = etUser.text.toString()
        var email = etMail.text.toString()
        var password = etPassword.text.toString()

        if(!userInputIsValid(user, email, password))
        {
            return
        }

        registerUser(user, email, password)
    }

    private fun userInputIsValid(user: String, email: String, password: String): Boolean {
        var inputIsValid = true

        if(user.isEmpty() || email.isEmpty() || password.isEmpty())
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

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun registerUser(user: String, email: String, password: String) {
        val mainIntent = Intent(this, MainActivity::class.java)
        mainIntent.putExtra(EXTRA_NAME, user);
        mainIntent.putExtra(EXTRA_EMAIL, email);
        startActivity(mainIntent)
        finish()

    }


    private fun onClickSignIn() {
        val loginIntent = Intent(this, SignInActivity::class.java)
        startActivity(loginIntent)
        finish()
    }
}
