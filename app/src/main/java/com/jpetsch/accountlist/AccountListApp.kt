package com.jpetsch.accountlist

import android.app.Application
import com.jpetsch.accountlist.di.accountModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AccountListApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@AccountListApp)
            modules(
                listOf(accountModule)
            )
        }
    }
}