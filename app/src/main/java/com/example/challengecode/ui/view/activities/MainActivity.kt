package com.example.challengecode.ui.view.activities

import android.content.Context
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.challengecode.R
import com.example.challengecode.databinding.ActivityMainBinding
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Setup
        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setup(provider ?: "")

        // Guardado de datos
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
            .edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()


    }

    private fun setup(provider: String) {
//        binding.btnSignOut.setonClickListener {
//
//            val prefs =
//                getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
//            prefs.clear()
//            prefs.apply()
//
//            if (provider == ProviderType.FACEBOOK.name) {
//                LoginManager.getInstance().logOut()
//            }
//
//            FirebaseAuth.getInstance().signOut()
//            onBackPressed()
//        }
    }

    override fun onBackPressed() {}
}