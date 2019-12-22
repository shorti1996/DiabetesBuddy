package com.wojciszke.diabetesbuddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wojciszke.diabetesbuddy.boiler.inTransaction
import com.wojciszke.diabetesbuddy.main.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prepareBottomNav()
    }

    private fun prepareBottomNav() {
        main_bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> showHome().let { true }
                R.id.nav_logs -> showHome().let { true }
                R.id.nav_stats -> showHome().let { true }
                else -> throw IllegalArgumentException("Invalid menu item selected from main bottom navigation bar")
            }
        }
        main_bottom_navigation.selectedItemId = R.id.nav_home
    }

    private fun showHome() {
        val homeFragment = supportFragmentManager.findFragmentByTag(HomeFragment.TAG)
            ?: HomeFragment.newInstance("", "")
        supportFragmentManager.inTransaction {
            replace(R.id.main_root, homeFragment, HomeFragment.TAG)
        }
    }
}

