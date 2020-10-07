package com.forntoh.livedata_validation.sample.other

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.forntoh.livedata_validation.sample.databinding.OtherFragmentBinding
import com.forntoh.livedata_validation.validation.LiveDataValidator

class OtherFragment : Fragment() {

    private val viewModel: OtherViewModel by viewModels()

    private lateinit var binding: OtherFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = with(OtherFragmentBinding.inflate(inflater, container, false)) {
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