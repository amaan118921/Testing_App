package com.example.testapp.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.example.testapp.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_pie.*
import org.eazegraph.lib.models.PieModel

class PieFragment: BaseFragment() {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_pie
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
    }

    private fun setData() {
        piechart.addPieSlice(
            PieModel(40F, resources.getColor(R.color.c1))
        )
        piechart.addPieSlice(
            PieModel(30F, resources.getColor(R.color.c2))
        )
        piechart.addPieSlice(
            PieModel(20F, resources.getColor(R.color.c3))
        )
        piechart.addPieSlice(
            PieModel(10F, resources.getColor(R.color.c4))
        )
    }
}