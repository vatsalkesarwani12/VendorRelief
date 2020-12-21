package com.CodeNaroNa.vendor.relief.FragmentsKotlin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.CodeNaroNa.vendor.relief.GlobalHelpers.Constants
import com.CodeNaroNa.vendor.relief.GlobalHelpers.Resource
import com.CodeNaroNa.vendor.relief.ViewModels.MainActivityViewModel
import com.CodeNaroNa.vendor.relief.ViewModels.VendorActivityViewModel
import com.CodeNaroNa.vendor.relief.activityKotlin.BaseActivity
import com.CodeNaroNa.vendor.relief.adapterKotlin.PrecAdapter
import com.CodeNaroNa.vendor.relief.databinding.FragmentPrecautionBinding

class PrecautionFragment : Fragment()
{
    companion object {
        @JvmStatic
        fun newInstance(fromClass: String) = PrecautionFragment().apply {
            arguments = Bundle().apply {
                putString(Constants.FROM_CLASS_KEY, fromClass)
            }
        }
    }

    private var fromClass: String = ""
    private var _binding: FragmentPrecautionBinding? = null
    private val binding get() = _binding!!
    private lateinit var precAdapter: PrecAdapter

    private lateinit var sharedViewModel : MainActivityViewModel
    private lateinit var sharedViewModel2: VendorActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fromClass = arguments?.getString(Constants.FROM_CLASS_KEY) ?: Constants.FROM_DEFAULT_CLASS

        if (fromClass == Constants.FROM_DEFAULT_CLASS)
            sharedViewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
        else
            sharedViewModel2 = ViewModelProvider(requireActivity()).get(VendorActivityViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPrecautionBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeView()
        setUpLiveDataObservers()
    }

    private fun initializeView() {
        if(fromClass==Constants.FROM_DEFAULT_CLASS)
        sharedViewModel.getPrecautionData()
        else
            sharedViewModel2.getPrecautionData()

        precAdapter = PrecAdapter()
        binding.precRecycle.apply {
            adapter = precAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setUpLiveDataObservers() {

        if(fromClass==Constants.FROM_DEFAULT_CLASS)
        {
            sharedViewModel.precautionData.observe(viewLifecycleOwner,{
                when(it)
                {
                    is Resource.Success ->{
                        precAdapter.differ.submitList(it.data!!)
                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(), ""+it.message!!, Toast.LENGTH_SHORT).show();
                    }
                    else ->{ }
                }
            })
        }
        else
        {
            sharedViewModel2.precautionData.observe(viewLifecycleOwner,{
                when(it)
                {
                    is Resource.Success ->{
                        precAdapter.differ.submitList(it.data!!)
                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(), ""+it.message!!, Toast.LENGTH_SHORT).show();
                    }
                    else ->{ }
                }
            })
        }

    }

    //ViewBinding variable should be set to null to avoid memory leaks
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}