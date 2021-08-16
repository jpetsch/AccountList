package com.jpetsch.accountlist.data.api

import com.jpetsch.accountlist.BuildConfig
import com.jpetsch.accountlist.data.model.Account
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface AccountService {
    @GET("getAccounts")
    fun getAllAccounts(): Call<List<Account>>


    companion object {
        var accountService: AccountService? = null

        fun getInstance() : AccountService {

            if (accountService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                accountService = retrofit.create(AccountService::class.java)
            }
            return accountService!!
        }
    }
}