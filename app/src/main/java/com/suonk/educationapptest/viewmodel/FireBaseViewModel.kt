package com.suonk.educationapptest.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineDispatcher

class FireBaseViewModel(private val dispatcher: CoroutineDispatcher) : ViewModel(),
    LifecycleObserver {

    private val LOG_TAG = "FireBaseViewModel"
    private var auth: FirebaseAuth? = null
//    private var storage: FirebaseStorage
//    private var storageReference: StorageReference
    var loading: MutableLiveData<Boolean> = MutableLiveData()

    init {
        auth = FirebaseAuth.getInstance()
//        storage = FirebaseStorage.getInstance()
//        storageReference = storage.reference
    }
}