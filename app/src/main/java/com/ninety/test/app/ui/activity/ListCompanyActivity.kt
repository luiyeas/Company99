package com.ninety.test.app.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ninety.test.R
import com.ninety.test.app.ui.adapter.CompanyAdapter
import com.ninety.test.data.model.Company
import com.ninety.test.presentation.list.contract.ListPresenterContract
import kotlinx.android.synthetic.main.activity_list_company.*
import org.koin.android.ext.android.inject

class ListCompanyActivity : AppCompatActivity(), ListPresenterContract.View, CompanyAdapter.CompanyClickListener {


    private val mPresenter: ListPresenterContract.Presenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_company)

        mPresenter.attach(this)
        mPresenter.create()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.destroy()
    }

    override fun initView() {
        // Configure RecyclerView
        vRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        // Configure Retry Button
        vRetryButton.setOnClickListener { mPresenter.retryButtonClick() }
    }

    override fun drawCompanyList(companyList: List<Company>) {
        vRecyclerView.adapter = CompanyAdapter(this, companyList, this)
    }

    override fun showHidewLoadingView(show: Boolean) {
        vLoadingView.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onCompanyClick(company: Company) {
        mPresenter.onCompanyClick(company)
    }

    override fun showHideErrorView(show: Boolean) {
        if (show) {
            vEmptyErrorView.visibility = View.VISIBLE
            vErrorEmptyTextView.text = getString(R.string.empty_message)
        } else {
            vEmptyErrorView.visibility = View.GONE
        }
    }
}
