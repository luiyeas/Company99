package com.ninety.test.app.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ninety.test.R
import com.ninety.test.data.model.Company
import kotlinx.android.synthetic.main.adapter_company.view.*

class CompanyAdapter(val context: Context, val listData: List<Company>, val clickListener: CompanyClickListener?) :
    RecyclerView.Adapter<CompanyAdapter.ViewHolder>() {

    interface CompanyClickListener {
        fun onCompanyClick(company: Company)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_company, parent, false))
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvCompanyName.text = listData[position].name ?: ""
        holder.tvCompanySharePrice.text = listData[position].sharePrice?.toString() ?: ""
        holder.tvCompanyRicAppl.text = listData[position].ric ?: ""
        holder.itemView.setOnClickListener { clickListener?.onCompanyClick(listData[position]) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCompanyName = view.companyNameTextView as TextView
        val tvCompanySharePrice = view.companySharePriceTextView as TextView
        val tvCompanyRicAppl = view.companyRicApplTextView as TextView
    }
}

