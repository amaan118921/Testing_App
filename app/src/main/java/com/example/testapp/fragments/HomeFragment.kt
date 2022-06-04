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
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment: BaseFragment(), View.OnClickListener {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_home
    }
    private var auth = FirebaseAuth.getInstance()
    private var series: LineGraphSeries<DataPoint>? = null
    private var dataPointArray: ArrayList<DataPoint> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSharedPreferences()
        bottomNavVisible()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        for(i in 1..30) {
            dataPointArray.add(DataPoint(i.toDouble(), i.toDouble()+10.0))
        }
        series = LineGraphSeries(
            dataPointArray.toTypedArray()
        )
        idGraphView.titleTextSize = 18F;
        idGraphView.addSeries(series)
        ivSignOut.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.ivSignOut ->  signOut()
        }
    }

    private fun signOut() {
        auth.signOut()
        bottomNavGone()
        deleteSharedPreferences(Constants.PHONE_LOCAL)
        finish()
        startActivity(Intent(requireContext(), MainActivity::class.java))
    }
}


