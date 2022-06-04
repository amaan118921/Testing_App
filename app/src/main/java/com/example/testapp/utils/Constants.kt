package com.example.testapp.utils

import androidx.fragment.app.Fragment
import com.example.testapp.fragments.HomeFragment
import com.example.testapp.fragments.LoginFragment
import com.example.testapp.fragments.OtpFragment
import com.example.testapp.fragments.PieFragment

class Constants {
    companion object {
        const val LOGIN_ID = "LOGIN_ID"
        const val HOME_ID = "HOME_ID"
        const val PIE_ID = "PIE_ID"
        const val OTP_ID = "OTP_ID"
        const val MOBILE = "MOBILE"
        const val APP_NAME = "TEST_APP"
        const val PHONE_LOCAL = "PHONE_LOCAL"

        fun getFragmentClass(id: String): Class<Fragment> {
            return when(id) {
                LOGIN_ID -> LoginFragment::class.java as Class<Fragment>
                HOME_ID -> HomeFragment::class.java as Class<Fragment>
                PIE_ID -> PieFragment::class.java as Class<Fragment>
                OTP_ID -> OtpFragment::class.java as Class<Fragment>
                else -> LoginFragment::class.java as Class<Fragment>
            }
        }
    }
}