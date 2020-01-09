package com.wojciszke.diabetesbuddy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wojciszke.diabetesbuddy.adding.AddingLogActivity
import com.wojciszke.diabetesbuddy.boiler.inTransaction
import com.wojciszke.diabetesbuddy.logs.LogsFragment
import com.wojciszke.diabetesbuddy.main.HomeFragment
import com.wojciszke.diabetesbuddy.phototaking.PhotoTakingActivity
import com.wojciszke.diabetesbuddy.stats.StatsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fabs_leyout.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prepareBottomNav()

        fab_add.setOnClickListener { startActivity(Intent(this, AddingLogActivity::class.java)) }
        fab_photo.setOnClickListener { startActivity(Intent(this, PhotoTakingActivity::class.java)) }
    }

    private fun prepareBottomNav() {
        main_bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> showHome().let { true }
                R.id.nav_logs -> showLogs().let { true }
                R.id.nav_stats -> showStats().let { true }
                else -> throw IllegalArgumentException("Invalid menu item selected from main bottom navigation bar")
            }
        }
        main_bottom_navigation.selectedItemId = R.id.nav_home
    }

    private fun showHome() {
        val fragment = supportFragmentManager.findFragmentByTag(HomeFragment.TAG)
                ?: HomeFragment.newInstance()
        supportFragmentManager.inTransaction {
            replace(R.id.main_root, fragment, HomeFragment.TAG)
        }
    }

    private fun showLogs() {
        val fragment = supportFragmentManager.findFragmentByTag(LogsFragment.TAG)
                ?: LogsFragment.newInstance()
        supportFragmentManager.inTransaction {
            replace(R.id.main_root, fragment, LogsFragment.TAG)
        }
    }

    private fun showStats() {
        val fragment = supportFragmentManager.findFragmentByTag(StatsFragment.TAG)
                ?: StatsFragment.newInstance("", "")
        supportFragmentManager.inTransaction {
            replace(R.id.main_root, fragment, StatsFragment.TAG)
        }
    }
}

