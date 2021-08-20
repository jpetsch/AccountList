package com.jpetsch.accountlist.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jpetsch.accountlist.data.api.AccountService
import com.jpetsch.accountlist.data.repository.AccountRepository
import com.jpetsch.accountlist.databinding.AccountFragmentBinding
import com.jpetsch.accountlist.ui.adapter.AccountAdapter
import com.jpetsch.accountlist.ui.viewmodels.account.AccountViewModel
import com.jpetsch.accountlist.ui.viewmodels.account.AccountViewModelFactory
import org.koin.android.ext.android.inject
import com.jpetsch.accountlist.R


class AccountListFragment : Fragment(R.layout.account_fragment) {
    private val TAG = "AccountListFragment"

    private lateinit var accountFragmentBinding: AccountFragmentBinding
    private lateinit var viewModel: AccountViewModel
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private val accountService: AccountService by inject()
    private val adapter: AccountAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        accountFragmentBinding = AccountFragmentBinding.inflate(layoutInflater, container,false)
        return accountFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
        viewModel = ViewModelProvider(this, AccountViewModelFactory(AccountRepository(accountService))).get(
            AccountViewModel::class.java)

        accountFragmentBinding.recyclerview.adapter = adapter

        swipeRefreshLayout.setOnRefreshListener {
            refreshAccountData()
        }

        viewModel.accountList.observe(viewLifecycleOwner, {
            Log.d(TAG, "onCreate: $it")
            if (it != null) {
                adapter.setAccountList(it)
            } else {
                Toast.makeText(context,R.string.no_account_data, Toast.LENGTH_LONG).show()
            }

            swipeRefreshLayout.isRefreshing = false
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, {

        })
        viewModel.getAllAccounts()
    }

    private fun refreshAccountData() {
        viewModel.getAllAccounts()
    }

}