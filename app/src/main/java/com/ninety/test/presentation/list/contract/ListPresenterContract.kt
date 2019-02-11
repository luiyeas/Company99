package com.ninety.test.presentation.list.contract

import com.ninety.test.data.model.Company
import com.ninety.test.presentation.BasePresenter

class ListPresenterContract {
    interface View : BasePresenter.View {
        fun showHidewLoadingView(show: Boolean)
        fun drawCompanyList(companyList: List<Company>)
        fun showHideErrorView(show: Boolean)
    }

    interface Presenter : BasePresenter.Presenter<View> {
        fun retryButtonClick()
        fun onCompanyClick(company: Company)
    }
}