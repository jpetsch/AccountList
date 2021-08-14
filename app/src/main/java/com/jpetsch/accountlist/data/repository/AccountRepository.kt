package com.jpetsch.accountlist.data.repository

import com.jpetsch.accountlist.data.api.AccountService

class AccountRepository constructor(private val accountService: AccountService) {

    fun getAllAccounts() = accountService.getAllAccounts()

}