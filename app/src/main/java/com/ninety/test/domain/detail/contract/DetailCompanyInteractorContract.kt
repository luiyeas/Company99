package com.ninety.test.domain.detail.contract

import com.ninety.test.data.model.Company
import com.ninety.test.domain.BaseInteractor

class DetailCompanyInteractorContract {

    interface Callback : BaseInteractor.Callback {
        fun onCompanySuccess(company: Company)
        fun onCompanyError()
    }

    interface Interactor : BaseInteractor.Interactor<DetailCompanyInteractorContract.Callback> {
        fun getCompanyDetail(companyId: Int?)
    }
}