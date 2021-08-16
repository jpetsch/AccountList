package com.jpetsch.accountlist.ui.viewmodels.Account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jpetsch.accountlist.R
import com.jpetsch.accountlist.data.repository.AccountRepository

class AccountViewModelFactory constructor(private val repository: AccountRepository): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AccountViewModel::class.java)) {
            AccountViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException(R.string.no_account_vm.toString())
        }
    }
}