package com.ninety.test.domain.list.contract

import com.ninety.test.data.model.Company
import com.ninety.test.domain.BaseInteractor

class ListCompanyInteractorContract {

    interface Callback : BaseInteractor.Callback {
        fun onCompanyListSuccess(listCompany: List<Company>)
        fun onCompanyListError()
    }

    interface Interactor : BaseInteractor.Interactor<ListCompanyInteractorContract.Callback> {
        fun getCompanyList()
        fun retryData()
    }

}