package com.forntoh.livedata_validation.sample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.forntoh.livedata_validation.sample.databinding.MainFragmentBinding
import com.forntoh.livedata_validation.validation.LiveDataValidator

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = with(MainFragmentBinding.inflate(inflater, container, false)) {
        lifecycleOwner = viewLifecycleOwner
        viewmodel = viewModel
        binding = this
        root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LiveDataValidator(requireContext()).observe {
            lifecycleOwner(viewLifecycleOwner)
            viewModel(viewModel)
            attachTo(binding.root)
        }
    }

}