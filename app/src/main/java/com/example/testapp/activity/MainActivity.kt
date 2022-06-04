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
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initSharedPreferences()
        verifyAuth()
        btmNavigation.setOnNavigationItemSelectedListener(this)
    }

    private fun verifyAuth() {
        if(getSharedPreferences(Constants.PHONE_LOCAL)!="") addFragment(Constants.HOME_ID)
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


    fun bottomNavVisible() = btmNavigation.makeVisible()
    fun bottomNavGone() = btmNavigation.makeGone()

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.btm_home -> {
                replaceFragment(Constants.HOME_ID)
                true
            }
            R.id.btm_pie -> {
                replaceFragment(Constants.PIE_ID)
                true
            }
            else -> false
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