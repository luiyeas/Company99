package com.ninety.test.domain.list

import com.ninety.test.data.model.Company
import com.ninety.test.data.repository.CompanyRepository
import com.ninety.test.domain.list.contract.ListCompanyInteractorContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class ListCompanyInteractor(val compositeDisposable: CompositeDisposable, val repository: CompanyRepository) :
    ListCompanyInteractorContract.Interactor {

    private var mCallBack: ListCompanyInteractorContract.Callback? = null

    override fun attachCallback(callback: ListCompanyInteractorContract.Callback) {
        mCallBack = callback
    }

    override fun run() {
        getCompanyList()
    }

    override fun destroy() {
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
    }

    override fun retryData() {
        getCompanyList()
    }

    override fun getCompanyList() {
        compositeDisposable.add(
            repository
                .getCompanyList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response: Response<List<Company>> ->
                    when {
                        response.isSuccessful && response.body() != null -> {
                            // Success
                            mCallBack?.onCompanyListSuccess(response.body() as List<Company>)
                        }
                        response.errorBody() != null -> {
                            // Server Error
                            mCallBack?.onCompanyListError()
                        }
                    }
                }, {
                    // Connection Error
                    mCallBack?.onCompanyListError()
                })
        )
    }
}