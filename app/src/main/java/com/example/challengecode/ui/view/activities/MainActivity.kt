package com.example.challengecode.ui.view.activities

import android.content.Context
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.challengecode.R
import com.example.challengecode.databinding.ActivityMainBinding
import com.example.challengecode.utils.ScreenSlideAdapter
import com.facebook.login.LoginManager
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val characterIcons =
        ArrayList<Int>(listOf(R.drawable.ic_character, R.drawable.ic_character_colored))
    private val eventsIcons =
        ArrayList<Int>(listOf(R.drawable.ic_calendar_white, R.drawable.ic_calendar_colored))

    private var loadingStateCount: Int = 0
    var loadingStateVisible: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUi()


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

        binding.mainScreen.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> {
                        binding.navBottom.getTabAt(0)?.setIcon(characterIcons[1])
                        binding.navBottom.getTabAt(1)?.setIcon(eventsIcons[0])
                    }
                    else -> {
                        binding.navBottom.getTabAt(0)?.setIcon(characterIcons[0])
                        binding.navBottom.getTabAt(1)?.setIcon(eventsIcons[1])
                    }
                }

            }
        })
    }

    private fun initUi() {
        binding.mainScreen.adapter = ScreenSlideAdapter(this)
        TabLayoutMediator(binding.navBottom, binding.mainScreen) { _, _ -> }.attach()
        setupPageIndicatorIcons()
        setTabLayoutTitle()
    }

    private fun setTabLayoutTitle() {
        binding.navBottom.getTabAt(0)?.text = getText(R.string.characterTitle)
        binding.navBottom.getTabAt(1)?.text = getText(R.string.eventTitle)
    }

    private fun setupPageIndicatorIcons() {
        val imageResId = ArrayList<Int>(listOf(characterIcons[0], eventsIcons[0]))
        imageResId.forEachIndexed { i, resId ->
            binding.navBottom.getTabAt(i)?.setIcon(resId)
        }
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