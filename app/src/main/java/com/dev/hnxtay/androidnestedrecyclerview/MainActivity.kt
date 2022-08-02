package com.dev.hnxtay.androidnestedrecyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.hnxtay.androidnestedrecyclerview.databinding.ActivityMainBinding
import com.dev.hnxtay.androidnestedrecyclerview.models.ChildItem
import com.dev.hnxtay.androidnestedrecyclerview.models.ParentItem
import com.dev.hnxtay.androidnestedrecyclerview.recyclerview.adapters.ParentAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val listChildItem = List(10) {
            ChildItem(it.toString())
        }
        val listParentItem = List(10) {
            ParentItem("Title $it", listChildItem)
        }
        binding.mainRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ParentAdapter(listParentItem)
            setHasFixedSize(true)
        }
        setContentView(binding.root)
    }
}