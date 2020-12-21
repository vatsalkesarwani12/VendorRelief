package com.CodeNaroNa.vendor.relief.FragmentsKotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.CodeNaroNa.vendor.relief.GlobalHelpers.Constants
import com.CodeNaroNa.vendor.relief.databinding.FragmentCronaSpreadDataBinding


class CoronaSpreadDataFragment : Fragment()
{
    private var _binding: FragmentCronaSpreadDataBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCronaSpreadDataBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.web.settings.apply {
            javaScriptEnabled = true
        }
        binding.web.loadUrl(Constants.VENDOR_RELIEF_WEBSITE)
    }
}