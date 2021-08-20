package com.jpetsch.accountlist.ui.viewmodels.account

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jpetsch.accountlist.data.model.Account
import com.jpetsch.accountlist.data.repository.AccountRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountViewModel constructor(private val repository: AccountRepository): ViewModel() {

    val accountList = MutableLiveData<List<Account>>()
    val errorMessage = MutableLiveData<String>()


    fun getAllAccounts() {
        val response = repository.getAllAccounts()
        response.enqueue(object : Callback<List<Account>> {
            override fun onResponse(call: Call<List<Account>>, response: Response<List<Account>>) {
                accountList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Account>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}