package com.dev.hnxtay.androidnestedrecyclerview.ui

import android.os.Parcelable
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var stateFragment: Parcelable? = null

    fun setState(state: Parcelable?) {
        stateFragment = state
    }
}