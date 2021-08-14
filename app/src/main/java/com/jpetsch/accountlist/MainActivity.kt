package com.jpetsch.accountlist

import android.accounts.Account
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jpetsch.accountlist.data.api.AccountService
import com.jpetsch.accountlist.data.repository.AccountRepository
import com.jpetsch.accountlist.databinding.ActivityMainBinding
import com.jpetsch.accountlist.ui.adapter.AccountAdapter
import com.jpetsch.accountlist.ui.viewmodels.Account.AccountViewModel
import com.jpetsch.accountlist.ui.viewmodels.Account.AccountViewModelFactory

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: AccountViewModel

    private val retrofitService = AccountService.getInstance()
    val adapter = AccountAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        viewModel = ViewModelProvider(this, AccountViewModelFactory(AccountRepository(retrofitService))).get(AccountViewModel::class.java)

        binding.includedRecyclerview.recyclerview.adapter = adapter

        viewModel.accountList.observe(this, {
            Log.d(TAG, "onCreate: $it")
            if (it != null) {
                adapter.setAccountList(it)
            } else {
                Toast.makeText(applicationContext,"received no Account Data - Check Network or API endpoint",Toast.LENGTH_LONG).show()
            }
        })

        viewModel.errorMessage.observe(this, {

        })
        viewModel.getAllAccounts()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}