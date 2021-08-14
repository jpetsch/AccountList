package com.jpetsch.accountlist.data.model

data class Account(val name: String, val iban: String, val balance: Float, val currency: String) {
}