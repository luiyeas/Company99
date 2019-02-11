package com.ninety.test.presentation.detail.contract

import com.ninety.test.data.model.Company
import com.ninety.test.presentation.BasePresenter

class DetailCompanyPresenterContract {
    interface View : BasePresenter.View {
        fun showHideLoadingView(show: Boolean)
        fun showCompany(company : Company)
        fun showError()
    }

    interface Presenter : BasePresenter.Presenter<View> {
        fun setCompanyId(companyId: Int?)
    }
}