package com.ninety.test.domain

class BaseInteractor {

    interface Interactor<in T> {
        fun attachCallback(callback: T)
        fun run()
        fun destroy()
    }

    interface Callback
}