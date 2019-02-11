package com.ninety.test.app.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ninety.test.R
import com.ninety.test.app.utils.Arguments.COMPANY_ID_ARGS
import com.ninety.test.data.model.Company
import com.ninety.test.presentation.detail.contract.DetailCompanyPresenterContract
import kotlinx.android.synthetic.main.activity_company_detail.*
import org.koin.android.ext.android.inject

class DetailCompanyActivity : AppCompatActivity(), DetailCompanyPresenterContract.View {

    private val mPresenter: DetailCompanyPresenterContract.Presenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_detail)

        mPresenter.attach(this)

        val mCompanyId = intent?.getIntExtra(COMPANY_ID_ARGS, 0)
        mPresenter.setCompanyId(mCompanyId)

        mPresenter.create()

    }

    override fun initView() {
        // Stuff
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.destroy()
    }

    override fun showHideLoadingView(show: Boolean) {
        vLoadingView.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showCompany(company: Company) {
        vNameTextView.text = company.name
        vPriceTextView.text = company.sharePrice?.toString()
        vDescriptionTextView.text = company.description
        vRicTextView.text = company.ric
    }

    override fun showError() {

    }

}