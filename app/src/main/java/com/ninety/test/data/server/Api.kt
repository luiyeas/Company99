package com.ninety.test.data.server

import com.mvp.kotlinmvp.data.server.ApiServiceInterface.Arguments.COMPANY_ID

object ApiConstants {
    const val HOST = "https://dev.ninetynine.com"

    const val COMPANY_LIST_ENDPOINT = "/testapi/1/companies"
    const val COMPANY_DETAIL_ENDPOINT = "/testapi/1/companies/{$COMPANY_ID}"
}

