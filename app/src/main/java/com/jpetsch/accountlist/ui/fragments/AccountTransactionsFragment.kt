package com.jpetsch.accountlist.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.jpetsch.accountlist.R
import com.jpetsch.accountlist.databinding.AdapterAccountBinding

import android.widget.TextView




class AccountTransactionsFragment: Fragment(R.layout.account_transactions_fragment) {
    private lateinit var adapterAccountBinding: AdapterAccountBinding
    private var accountId: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val accountName: TextView = getView()?.findViewById(R.id.name) as TextView
        val accountBalance: TextView = getView()?.findViewById(R.id.balance) as TextView
        val accountCurrency: TextView = getView()?.findViewById(R.id.currency) as TextView
        val accountIban: TextView = getView()?.findViewById(R.id.iban) as TextView
        accountName.text = adapterAccountBinding.name.text
        accountBalance.text = adapterAccountBinding.balance.text
        accountCurrency.text = adapterAccountBinding.currency.text
        accountIban.text = adapterAccountBinding.iban.text
    }


    fun setAccount(adapterAccountBinding: AdapterAccountBinding, id: Int) {
        this.adapterAccountBinding = adapterAccountBinding
        this.accountId = id
    }
}