package com.example.testapp.activity

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.testapp.R
import com.example.testapp.utils.Constants
import com.example.testapp.utils.makeGone
import com.example.testapp.utils.makeVisible
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initSharedPreferences()
        verifyAuth()
    }

    private fun verifyAuth() {
        if(getSharedPreferences(Constants.EMAIL)!="") addFragment(Constants.SPLASH_ID)
        else addFragment(Constants.LOGIN_ID)
    }

    fun getReqFragmentManager(): FragmentManager = supportFragmentManager

    private fun addFragment(id: String) {
        getReqFragmentManager().commit {
            setReorderingAllowed(true)
            add(R.id.fragment_container_view, Constants.getFragmentClass(id), null, id)
        }
    }

    private fun replaceFragment(id: String) {
        getReqFragmentManager().commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container_view, Constants.getFragmentClass(id), null)
        }
    }


    private fun initSharedPreferences() {
        sharedPreferences = getSharedPreferences(Constants.APP_NAME, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    private fun getSharedPreferences(key: String): String {
        return sharedPreferences.getString(key, "") ?: ""
    }
}