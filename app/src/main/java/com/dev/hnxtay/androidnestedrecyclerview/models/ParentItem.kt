package com.dev.hnxtay.androidnestedrecyclerview.models

import android.os.Parcelable

data class ParentItem(
    val title: String,
    val items: List<ChildItem>,
    val state : Parcelable?
)