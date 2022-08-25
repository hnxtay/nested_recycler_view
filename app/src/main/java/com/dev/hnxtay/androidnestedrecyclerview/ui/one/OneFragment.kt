package com.dev.hnxtay.androidnestedrecyclerview.ui.one

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.hnxtay.androidnestedrecyclerview.databinding.FragmentOneBinding
import com.dev.hnxtay.androidnestedrecyclerview.models.ChildItem
import com.dev.hnxtay.androidnestedrecyclerview.models.ParentItem
import com.dev.hnxtay.androidnestedrecyclerview.recyclerview.adapters.ParentAdapter
import com.dev.hnxtay.androidnestedrecyclerview.ui.MainViewModel
import com.google.android.material.snackbar.Snackbar

class OneFragment : Fragment() {

    private val mainViewModel by activityViewModels<MainViewModel>()

    companion object {
        fun newInstance() = OneFragment()
    }

    private lateinit var viewModel: OneViewModel
    private var _binding: FragmentOneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOneBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[OneViewModel::class.java]

        super.onViewCreated(view, savedInstanceState)

        val listChildItem = List(10) {
            ChildItem((it + 1).toString())
        }
        val listParentItem = List(10) {
            ParentItem("Title ${it + 1}", listChildItem, mainViewModel.stateFragment)
        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ParentAdapter(
                listParentItem,
                onParentItemClickListener = {
                    Snackbar.make(binding.root, it.title, Snackbar.LENGTH_LONG).show()
                },
                onChildItemClickListener = {
                    Snackbar.make(binding.root, "Item ${it.title}", Snackbar.LENGTH_LONG).show()
                },
            )
            setHasFixedSize(true)
        }
        mainViewModel.stateFragment?.let { state ->
            binding.recyclerView.layoutManager?.onRestoreInstanceState(state)
            mainViewModel.stateFragment = null
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val listState = binding.recyclerView.layoutManager?.onSaveInstanceState()
        listState?.let { mainViewModel.setState(it) }

    }
}