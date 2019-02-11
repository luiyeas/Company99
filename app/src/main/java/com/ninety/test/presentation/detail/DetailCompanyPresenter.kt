package com.ninety.test.presentation.detail


import com.ninety.test.data.model.Company
import com.ninety.test.domain.detail.contract.DetailCompanyInteractorContract
import com.ninety.test.presentation.detail.contract.DetailCompanyPresenterContract

class DetailCompanyPresenter(val interactor: DetailCompanyInteractorContract.Interactor) :

    DetailCompanyPresenterContract.Presenter, DetailCompanyInteractorContract.Callback {

    private lateinit var mView: DetailCompanyPresenterContract.View
    private var mCompanyId: Int? = null

    override fun attach(view: DetailCompanyPresenterContract.View) {
        mView = view
        mView.initView()
    }

    override fun create() {
        interactor.attachCallback(this)
        interactor.getCompanyDetail(mCompanyId)
    }

    override fun resume() {
        // Stuff
    }

    override fun destroy() {
        interactor.destroy()
    }

    override fun setCompanyId(companyId: Int?) {
        mCompanyId = companyId
    }

    override fun onCompanyError() {

    }

    override fun onCompanySuccess(company: Company) {
        mView.showHideLoadingView(false)
        mView.showCompany(company)
    }
}