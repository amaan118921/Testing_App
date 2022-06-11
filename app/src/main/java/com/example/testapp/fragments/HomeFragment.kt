package com.example.testapp.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.SyncStateContract
import android.view.View
import com.example.testapp.R
import com.example.testapp.activity.MainActivity
import com.example.testapp.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment: BaseFragment(), View.OnClickListener {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_home
    }
    private var auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSharedPreferences()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onClick(view: View?) {
        when(view?.id) {

        }
    }

}


