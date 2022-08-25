package com.dev.hnxtay.androidnestedrecyclerview.ui.three

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dev.hnxtay.androidnestedrecyclerview.databinding.FragmentThreeBinding

class ThreeFragment : Fragment() {

    companion object {
        fun newInstance() = ThreeFragment()
    }

    private lateinit var viewModel: ThreeViewModel
    private var _binding: FragmentThreeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThreeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ThreeViewModel::class.java]
    }

}