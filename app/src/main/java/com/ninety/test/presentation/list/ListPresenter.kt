package com.ninety.test.presentation.list

import com.ninety.test.app.navigator.Navigator
import com.ninety.test.data.model.Company
import com.ninety.test.domain.list.contract.ListCompanyInteractorContract
import com.ninety.test.presentation.list.contract.ListPresenterContract

class ListPresenter(val navigator: Navigator, val interactor: ListCompanyInteractorContract.Interactor) :
    ListPresenterContract.Presenter,
    ListCompanyInteractorContract.Callback {

    private lateinit var mView: ListPresenterContract.View

    override fun attach(view: ListPresenterContract.View) {
        mView = view
        mView.initView()
    }

    override fun create() {
        interactor.attachCallback(this)
        interactor.run()
    }

    override fun resume() {
        // Stuff
    }

    override fun destroy() {
        interactor.destroy()
    }

    override fun onCompanyListSuccess(listCompany: List<Company>) {
        if (listCompany.isEmpty()) {
            mView.showHideErrorView(true)
        } else {
            mView.drawCompanyList(listCompany.sortedWith(compareBy { it.sharePrice }))
        }
        mView.showHidewLoadingView(false)
    }

    override fun onCompanyListError() {
        mView.showHidewLoadingView(false)
        mView.showHideErrorView(true)
    }

    override fun retryButtonClick() {
        mView.showHideErrorView(false)
        mView.showHidewLoadingView(true)
        interactor.retryData()
    }

    override fun onCompanyClick(company: Company) {
        navigator.navigateToDetailActivity(company.id)
    }
}