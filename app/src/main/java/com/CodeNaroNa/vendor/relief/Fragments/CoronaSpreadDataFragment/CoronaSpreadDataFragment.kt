package com.CodeNaroNa.vendor.relief.Fragments.CoronaSpreadDataFragment

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCronaSpreadDataBinding.inflate(inflater,container,false)
        return _binding!!.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding!!.web.settings.apply {
            javaScriptEnabled = true
        }
        _binding!!.web.loadUrl(Constants.VENDOR_RELIEF_WEBSITE)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}