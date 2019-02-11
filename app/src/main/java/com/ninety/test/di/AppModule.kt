package com.ninety.test.di

import com.mvp.kotlinmvp.data.server.ApiServiceInterface
import com.ninety.test.app.navigator.Navigator
import com.ninety.test.data.repository.CompanyRepository
import com.ninety.test.domain.detail.DetailCompanyInteractor
import com.ninety.test.domain.detail.contract.DetailCompanyInteractorContract
import com.ninety.test.domain.list.ListCompanyInteractor
import com.ninety.test.domain.list.contract.ListCompanyInteractorContract
import com.ninety.test.presentation.detail.DetailCompanyPresenter
import com.ninety.test.presentation.detail.contract.DetailCompanyPresenterContract
import com.ninety.test.presentation.list.ListPresenter
import com.ninety.test.presentation.list.contract.ListPresenterContract
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module


val mainModule = module {
    factory<ListPresenterContract.Presenter> { ListPresenter(navigator = get(), interactor = get()) }
    factory<DetailCompanyPresenterContract.Presenter> { DetailCompanyPresenter(interactor = get()) }
}

val interactorModule = module {
    factory<ListCompanyInteractorContract.Interactor> {
        ListCompanyInteractor(
            compositeDisposable = get(),
            repository = get()
        )
    }

    factory<DetailCompanyInteractorContract.Interactor> {
        DetailCompanyInteractor(
            compositeDisposable = get(),
            repository = get()
        )
    }
}

val navigatorModule = module {
    single { Navigator(context = androidContext()) }
}

val compositeModule = module {
    factory { CompositeDisposable() }
}

val networkModule = module {
    single { ApiServiceInterface.create() }
}

val repositoryModule = module {
    single { CompanyRepository(serviceInterface = get()) }
}