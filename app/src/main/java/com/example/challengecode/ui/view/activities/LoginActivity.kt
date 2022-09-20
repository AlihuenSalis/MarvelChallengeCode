package com.example.challengecode.ui.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.challengecode.R
import com.example.challengecode.databinding.ActivityLoginBinding
import com.example.challengecode.utils.ProviderType
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val callbackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkSession()


//        binding.signUpButton.setOnClickListener {
//            if (binding.username.text!!.isNotEmpty() && binding.password.text!!.isNotEmpty()) {
//                binding.fieldUsername.error
//                FirebaseAuth.getInstance()
//                    .createUserWithEmailAndPassword(binding.username.text.toString(), binding.password.text.toString())
//                    .addOnCompleteListener {
//
//                        if (it.isSuccessful) {
//                            Toast.makeText(this, "creado", Toast.LENGTH_SHORT)
//                        } else {
//                            showAlertDialog(it.exception?.message ?: ERROR_MESSAGE)
//                        }
//
//                    }
//            } else {
//                if (binding.username.text!!.isEmpty()) {
//                    binding.username.error = REQUIRED
//                } else if (binding.password.text!!.isEmpty()) {
//                    binding.password.error = REQUIRED
//                }
//            }
//        }

        binding.signFacebookButton.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"))

            LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult>{
                    override fun onSuccess(result: LoginResult?) {
                        result?.let {
                            val token = it.accessToken
                            val credential = FacebookAuthProvider.getCredential(token.token)
                            FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
                                if (it.isSuccessful) {
                                    goToMainActivity(it.result?.user?.email ?: "", ProviderType.FACEBOOK)
                                } else {
                                    showAlertDialog(it.exception?.message ?: ERROR_MESSAGE)
                                }
                            }
                        }
                    }

                    override fun onCancel() {
                    }

                    override fun onError(error: FacebookException?) {
                        showAlertDialog(ERROR_MESSAGE)
                    }

                })
        }

        binding.signInButton.setOnClickListener {
            if (binding.username.text!!.isNotEmpty() && binding.password.text!!.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(binding.username.text.toString(), binding.password.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            goToMainActivity(it.result?.user?.email ?: "", ProviderType.BASIC)
                        } else {
                            showAlertDialog(it.exception?.message ?: ERROR_MESSAGE)
                        }
                    }
            } else {
                if (binding.username.text!!.isEmpty()) {
                    binding.username.error = REQUIRED
                } else if (binding.password.text!!.isEmpty()) {
                    binding.fieldPasswordLogin.error = REQUIRED
                }
            }

//            if (binding.chkRememberMe.isChecked) {
//                val prefs1 = getSharedPreferences(ChallengeApplication.PREFSREMEMBER, MODE_PRIVATE)
//                val editor = prefs1.edit()
//                editor.putString("remember", "true")
//                editor.apply()
//            } else if (!binding.chkRememberMe.isChecked) {
//                val prefs1 = getSharedPreferences(ChallengeApplication.PREFSREMEMBER, MODE_PRIVATE)
//                val editor = prefs1.edit()
//                editor.putString("remember", "false")
//                editor.apply()
//            }
        }
    }

    private fun checkSession() {
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)

        if (email != null && provider != null) {
            goToMainActivity(email, ProviderType.valueOf(provider))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun showAlertDialog(messsage: String?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(messsage)
        builder.setPositiveButton("Acetar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun goToMainActivity(email: String, provider: ProviderType) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(intent)
    }

    companion object {
        val REQUIRED = "Debe ingresar un valor"
        val ERROR_MESSAGE = "Se ha producido un error autenticando al usuario"
    }
}