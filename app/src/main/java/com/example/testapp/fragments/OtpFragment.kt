package com.example.testapp.fragments

import android.app.ProgressDialog
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.testapp.R
import com.example.testapp.utils.Constants
import com.google.android.material.card.MaterialCardView
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import kotlinx.android.synthetic.main.fragment_otp.*
import java.util.concurrent.TimeUnit

class OtpFragment: BaseFragment(), View.OnClickListener {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_otp
    }

    private var phone: String? = null
    private lateinit var userToken: PhoneAuthProvider.ForceResendingToken
    private var userid: String? = null
    private lateinit var dialog: ProgressDialog
    private var auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        phone = arguments?.getString(Constants.MOBILE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTextChanger()
        initSharedPreferences()
        phone?.let {
            tvEnterMobile.text = "${getString(R.string.enter_the_otp_sent_on)} $it"
        }
        btnVerifyOtp.setOnClickListener(this)
        btnBack.setOnClickListener(this)
        ed1.requestFocus()
        setFocusChanger()
        getCallbacks()
    }

    private fun setFocusChanger() {
        ed1.setOnFocusChangeListener { view, b ->
            if(b) {
                resetCard()
                checkCard(et1)
            }
        }
        ed2.setOnFocusChangeListener { view, b ->
            if(b) {
                resetCard()
                checkCard(et2)
            }
        }
        ed3.setOnFocusChangeListener { view, b ->
            if(b) {
                resetCard()
                checkCard(et3)
            }
        }
        ed4.setOnFocusChangeListener { view, b ->
            if(b) {
                resetCard()
                checkCard(et4)
            }
        }
        ed5.setOnFocusChangeListener { view, b ->
            if(b) {
                resetCard()
                checkCard(et5)
            }
        }
        ed6.setOnFocusChangeListener { view, b ->
            if(b) {
                resetCard()
                checkCard(et6)
            }
        }
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.btnVerifyOtp -> verify()
            R.id.btnBack -> popBackStack()
        }
    }

    private fun verify() {
        val otp = ed1.text.toString().trim() + ed2.text.toString().trim() + ed3.text.toString().trim() +
                ed4.text.toString().trim() + ed5.text.toString().trim() + ed6.text.toString().trim()
        if(otp.length<6) {showToast("Enter valid 6 digit otp")}
        else{
            val credential = userid?.let { PhoneAuthProvider.getCredential(it, otp) }
            callDialog()
            credential?.let { signInWithPhoneAuthCredential(it) }
        }
    }


    private fun checkCard(card: MaterialCardView?) {
        card?.let {
            it.apply {
                strokeColor = resources.getColor(R.color.app_theme)
                strokeWidth = 6
            }
        }
    }

    private fun setTextChanger() {
        ed1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (ed1.text.length == 1) {
                    ed2.requestFocus()
                }
            }

        })
        ed2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }


            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }


            override fun afterTextChanged(s: Editable?) {
                if (ed2.text.length == 1) {
                    ed3.requestFocus()
                } else if(ed2.text.isEmpty()) ed1.requestFocus()
            }

        })
        ed3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (ed3.text.length == 1) {
                    ed4.requestFocus()
                } else if(ed3.text.isEmpty()) ed2.requestFocus()
            }

        })
        ed4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (ed4.text.length == 1) {
                    ed5.requestFocus()
                } else if(ed4.text.isEmpty()) ed3.requestFocus()
            }

        })
        ed5.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(ed5.text.length == 1) {
                    ed6.requestFocus()
                } else if(ed5.text.isEmpty()) ed4.requestFocus()
            }

        })

        ed6.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
              if(ed6.text.isEmpty()) ed5.requestFocus()
            }

        })
    }

    private fun resetCard() {
        et1.apply {
            strokeWidth = 0
        }
        et2.apply {
            strokeWidth = 0
        }
        et3.apply {
            strokeWidth = 0
        }
        et4.apply {
            strokeWidth = 0
        }
        et5.apply {
            strokeWidth = 0
        }
        et6.apply {
            strokeWidth = 0
        }
    }

    private fun getCallbacks() {
        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                showToast("Verification Successful")
                signInWithPhoneAuthCredential(p0)
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                if (p0 is FirebaseAuthInvalidCredentialsException) {
                    showToast("Something went wrong, please try again later")
                } else {
                    showToast("Something went wrong, please try again later")
                }
                popBackStack()
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
                userid = p0
                userToken = p1
            }
        }
        val reqPhone = "+91$phone"
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(reqPhone).setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(callbacks)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }



    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential).addOnCompleteListener { p0 ->
            if (p0.isSuccessful) {
                dialog.dismiss()
                phone?.let { setSharedPreferences(Constants.PHONE_LOCAL, it) }
                val newUser = p0.result!!.additionalUserInfo!!.isNewUser
                if (newUser) {
                    showToast("Verification Successful")
                } else {
                    showToast("Welcome Back")
                }
                replaceFragment(Constants.HOME_ID,null)
            } else {
                dialog.dismiss()
                showToast("Incorrect otp")
                popBackStack()
            }
        }
    }

    private fun callDialog() {
        dialog = ProgressDialog(requireContext())
        dialog.setMessage("Please Wait")
        dialog.setCancelable(false)
        dialog.show()
    }

}