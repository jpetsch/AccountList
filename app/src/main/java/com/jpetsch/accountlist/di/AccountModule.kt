package com.jpetsch.accountlist.di

import com.jpetsch.accountlist.ui.adapter.AccountAdapter
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val accountModule = module {
    single { AccountAdapter(androidContext(),)}
}