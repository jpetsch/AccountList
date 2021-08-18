package com.jpetsch.accountlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.jpetsch.accountlist.databinding.ActivityMainBinding
import com.jpetsch.accountlist.ui.fragments.AccountListFragment
import com.jpetsch.accountlist.ui.fragments.SettingsFragment


class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private lateinit var mainBinding: ActivityMainBinding

    private val accountListFragment = AccountListFragment()
    private val settingsFragment = SettingsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        setSupportActionBar(mainBinding.toolbar)

        switchFragment(R.id.main_fragment_container, accountListFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_account_list -> {
                switchFragment(R.id.main_fragment_container, accountListFragment)
                return true
            }
            R.id.action_settings -> {
                switchFragment(R.id.main_fragment_container, settingsFragment)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun switchFragment(layoutId: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(layoutId, fragment)
            commit()
        }
    }
}