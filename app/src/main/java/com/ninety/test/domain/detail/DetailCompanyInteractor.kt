package com.ninety.test.domain.detail

import com.ninety.test.app.utils.Constant.RETRY_TIME
import com.ninety.test.data.model.Company
import com.ninety.test.data.repository.CompanyRepository
import com.ninety.test.domain.detail.contract.DetailCompanyInteractorContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.util.concurrent.TimeUnit

class DetailCompanyInteractor(val compositeDisposable: CompositeDisposable, val repository: CompanyRepository) :
    DetailCompanyInteractorContract.Interactor {

    private var mCallBack: DetailCompanyInteractorContract.Callback? = null

    override fun attachCallback(callback: DetailCompanyInteractorContract.Callback) {
        mCallBack = callback
    }

    override fun run() {
        // Stuff
    }

    override fun destroy() {
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
    }

    override fun getCompanyDetail(companyId: Int?) {
        val obsCompany = repository.getCompanyDetail(companyId ?: 0)

        compositeDisposable.add(
            obsCompany
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response: Response<Company> ->
                    when {
                        response.isSuccessful && response.body() != null -> {
                            // Success
                            mCallBack?.onCompanySuccess(response.body() as Company)
                        }
                        response.errorBody() != null -> {
                            // Server Error
                            mCallBack?.onCompanyError()
                        }
                    }
                }, {
                    mCallBack?.onCompanyError()
                })
        )

        compositeDisposable.add(
            obsCompany
                .delay(RETRY_TIME, TimeUnit.SECONDS)
                .repeat()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response: Response<Company> ->
                    if (response.isSuccessful && response.body() != null) {
                        mCallBack?.onCompanySuccess(response.body() as Company)
                    }
                }, {
                    // Stuff
                })
        )
    }
}