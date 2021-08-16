package com.jpetsch.accountlist.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jpetsch.accountlist.R
import com.jpetsch.accountlist.data.model.Account
import com.jpetsch.accountlist.databinding.AdapterAccountBinding

class AccountAdapter(private val context: Context): RecyclerView.Adapter<AccountViewHolder>() {

    var accounts = mutableListOf<Account>()

    @SuppressLint("NotifyDataSetChanged")
    fun setAccountList(accounts: List<Account>) {
        this.accounts = accounts.toMutableList()
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = AdapterAccountBinding.inflate(inflater, parent, false)
        return AccountViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val account = accounts[position]
        holder.binding.name.text = account.name
        holder.binding.iban.text = account.iban
        holder.binding.balance.text = account.balance.toString()
        holder.binding.currency.text = account.currency

        if (account.balance <= 0.0F) {
            holder.binding.balance.setTextColor(ContextCompat.getColor(context, R.color.spk_red))
            holder.binding.currency.setTextColor(ContextCompat.getColor(context, R.color.spk_red))
        } else {
            holder.binding.balance.setTextColor(ContextCompat.getColor(context, R.color.spk_green))
            holder.binding.currency.setTextColor(ContextCompat.getColor(context, R.color.spk_green))
        }
    }

    override fun getItemCount(): Int {
        return accounts.size
    }
}

class AccountViewHolder(val binding: AdapterAccountBinding) : RecyclerView.ViewHolder(binding.root) {

}