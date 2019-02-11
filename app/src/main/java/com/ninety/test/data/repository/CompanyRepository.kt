package com.ninety.test.data.repository

import com.mvp.kotlinmvp.data.server.ApiServiceInterface
import com.ninety.test.data.model.Company
import io.reactivex.Observable
import retrofit2.Response

class CompanyRepository(private val serviceInterface: ApiServiceInterface) {

    fun getCompanyList(): Observable<Response<List<Company>>> {
        return serviceInterface.getCompanyList()
    }

    fun getCompanyDetail(companyDetail: Int): Observable<Response<Company>> {
        return serviceInterface.getCompanyDetail(companyDetail)
    }

}