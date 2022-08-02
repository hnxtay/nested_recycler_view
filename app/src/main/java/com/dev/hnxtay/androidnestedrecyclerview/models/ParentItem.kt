package com.dev.hnxtay.androidnestedrecyclerview.models

data class ParentItem(
    val title: String,
    val items: List<ChildItem>,
)