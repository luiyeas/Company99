package com.mvp.kotlinmvp.data.server


import com.mvp.kotlinmvp.data.server.ApiServiceInterface.Arguments.COMPANY_ID
import com.ninety.test.BuildConfig
import com.ninety.test.data.model.Company
import com.ninety.test.data.server.ApiConstants
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServiceInterface {

    object Arguments {
        const val COMPANY_ID: String = "companyId"
    }

    @GET(ApiConstants.COMPANY_LIST_ENDPOINT)
    fun getCompanyList(): Observable<Response<List<Company>>>

    @GET(ApiConstants.COMPANY_DETAIL_ENDPOINT)
    fun getCompanyDetail(@Path(COMPANY_ID) companyId: Int): Observable<Response<Company>>


    companion object {

        fun create(): ApiServiceInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                .baseUrl(ApiConstants.HOST)
                .client(provideOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiServiceInterface::class.java)
        }

        fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient()
                .newBuilder()
                .addInterceptor(HttpLoggingInterceptor()
                    .apply {
                        level =
                            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                    })
                .addNetworkInterceptor { chain ->
                    val request =
                        chain
                            .request()
                            .newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .build()
                    chain.proceed(request)
                }
                .build()
        }
    }

}