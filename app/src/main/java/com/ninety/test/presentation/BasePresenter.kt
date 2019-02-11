package com.ninety.test.presentation

class BasePresenter {
    interface Presenter<in T> {
        fun attach(view: T)
        fun create()
        fun resume()
        fun destroy()
    }

    interface View {
        fun initView()
    }
}