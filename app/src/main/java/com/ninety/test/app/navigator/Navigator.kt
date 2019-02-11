package com.ninety.test.app.navigator

import android.content.Context
import android.content.Intent
import com.ninety.test.app.ui.activity.DetailCompanyActivity
import com.ninety.test.app.utils.Arguments.COMPANY_ID_ARGS

class Navigator(val context: Context) {

    fun navigateToDetailActivity(companyId: Int?) {
        val intent = Intent(context, DetailCompanyActivity::class.java)
        intent.putExtra(COMPANY_ID_ARGS, companyId)
        context.startActivity(intent)
    }

}