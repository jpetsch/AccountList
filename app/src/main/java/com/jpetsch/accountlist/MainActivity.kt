package com.jpetsch.accountlist

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jpetsch.accountlist.data.api.AccountService
import com.jpetsch.accountlist.data.repository.AccountRepository
import com.jpetsch.accountlist.databinding.ActivityMainBinding
import com.jpetsch.accountlist.ui.adapter.AccountAdapter
import com.jpetsch.accountlist.ui.viewmodels.Account.AccountViewModel
import com.jpetsch.accountlist.ui.viewmodels.Account.AccountViewModelFactory
import org.koin.android.ext.android.inject




class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: AccountViewModel

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private val retrofitService = AccountService.getInstance()
    private val adapter: AccountAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)

        viewModel = ViewModelProvider(this, AccountViewModelFactory(AccountRepository(retrofitService))).get(AccountViewModel::class.java)

        binding.includedRecyclerview.recyclerview.adapter = adapter

        swipeRefreshLayout.setOnRefreshListener {
            refreshAccountData()
        }

        viewModel.accountList.observe(this, {
            Log.d(TAG, "onCreate: $it")
            if (it != null) {
                adapter.setAccountList(it)
                Toast.makeText(applicationContext,R.string.received_account_data,Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext,R.string.no_account_data,Toast.LENGTH_LONG).show()
            }

            swipeRefreshLayout.isRefreshing = false
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
            R.id.menu_refresh -> {
                swipeRefreshLayout.isRefreshing = true
                refreshAccountData()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun refreshAccountData() {
        viewModel.getAllAccounts()
    }
}