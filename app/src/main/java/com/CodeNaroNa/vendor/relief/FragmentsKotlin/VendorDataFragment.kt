package com.CodeNaroNa.vendor.relief.FragmentsKotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.CodeNaroNa.vendor.relief.ViewModels.MainActivityViewModel
import com.CodeNaroNa.vendor.relief.databinding.FragmentVendorDataBinding


class VendorDataFragment : Fragment() {
    private var _binding: FragmentVendorDataBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentVendorDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialiseView()
    }

    private fun initialiseView() {
        val data = sharedViewModel.selectedUserData

        binding.apply {
            vnameFrag.text = data.shopName
            vaddressFrag.text = data.address
            vcategoryFrag.text = data.shopCategory
            vcityFrag.text = data.city
            vstateFrag.text = data.state
            vphoneFrag.text = data.phoneNumber
            vopenFrag.text = data.openTime
            vcloseFrag.text = data.closeTime
        }

        /**
         *  [Bug-Fix]Don't remove this empty onClickLister,
         *  if removed the onClick events will be sent to back Stack fragments thus opening the same fragment
         *  when user click on the view
         */
        binding.root.setOnClickListener {
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}