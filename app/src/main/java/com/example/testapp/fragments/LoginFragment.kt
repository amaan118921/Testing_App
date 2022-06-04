package com.example.testapp.fragments

import android.os.Bundle
import android.view.View
import com.example.testapp.R
import com.example.testapp.utils.Constants
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment: BaseFragment(), View.OnClickListener {

    override fun getLayoutRes(): Int {
        return R.layout.fragment_login
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnNext.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.btnNext -> validate()
        }
    }

    private fun validate() {
        val phone = etMobile.text.toString().trim()
        if(phone.isEmpty()) showToast("Enter 10 digit phone number")
        else {
            Bundle().apply {
                putString(Constants.MOBILE, phone)
                addFragment(Constants.OTP_ID, this)
            }
        }
    }
}