package com.example.testapp.activity.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newapp.eventbus.MessageEvent
import com.example.testapp.model.Users
import com.example.testapp.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus

class TestViewModel : ViewModel() {


    private var auth = FirebaseAuth.getInstance()
    private var database = FirebaseDatabase.getInstance()
    private lateinit var ref: DatabaseReference

    fun insertData(name: String, email: String, phone: String) {
        ref = database.reference.child("users").child(auth.currentUser?.uid!!)
        viewModelScope.launch {
            Users().apply {
                this.name = name
                this.email = email
                ref.push().setValue(this).addOnCompleteListener {
                    if (it.isSuccessful) EventBus.getDefault()
                        .post(MessageEvent(Constants.DATA_SUCCESS))
                    else EventBus.getDefault().post(MessageEvent(Constants.DATA_FAILURE))
                }
            }
        }
    }


}