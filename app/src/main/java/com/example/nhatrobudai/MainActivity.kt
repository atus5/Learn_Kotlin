package com.example.nhatrobudai

import MailFragment
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.nhatrobudai.Fragment.GroupFragment
import com.example.nhatrobudai.Fragment.HistoryFragment

import com.example.nhatrobudai.Fragment.SettingFragment
import com.example.nhatrobudai.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }




        loadFragment(NewHome()) // default
        binding.bottomNav.setOnItemSelectedListener { it ->
            when (it.itemId) {
                R.id.nav_home -> loadFragment(NewHome())
                R.id.nav_group -> loadFragment(GroupFragment())
                R.id.nav_history -> loadFragment(HistoryFragment())
                R.id.nav_mail -> {
                    val prefs = getSharedPreferences("USER", MODE_PRIVATE)
                    val userName = prefs.getString("username", "") ?: ""
                    val role = prefs.getString("role", "user") ?: "user" // default là user
                    loadFragment(MailFragment())
                }
                R.id.nav_setting ->   loadFragment(SettingFragment())


            }
            true
        }


    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }
}