package com.CodeNaroNa.vendor.relief.FragmentsKotlin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.CodeNaroNa.vendor.relief.GlobalHelpers.Resource
import com.CodeNaroNa.vendor.relief.ViewModels.MainActivityViewModel
import com.CodeNaroNa.vendor.relief.activityKotlin.BaseActivity
import com.CodeNaroNa.vendor.relief.adapterKotlin.PrecAdapter
import com.CodeNaroNa.vendor.relief.databinding.FragmentPrecautionBinding

class PrecautionFragment : Fragment()
{
    private var _binding: FragmentPrecautionBinding? = null
    private val binding get() = _binding!!
    private lateinit var precAdapter: PrecAdapter

    private lateinit var sharedViewModel : MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
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
        sharedViewModel.getPrecautionData()

        precAdapter = PrecAdapter()
        binding.precRecycle.apply {
            adapter = precAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setUpLiveDataObservers() {
        sharedViewModel.precautionData.observe(viewLifecycleOwner,{
            when(it)
            {
                is Resource.Success ->{
                    precAdapter.differ.submitList(it.data!!)
                }
                is Resource.Error -> {
                    Log.d("SSSS",""+it.message!!)
                }
                else ->{

                }
            }
        })
    }

    //ViewBinding variable should be set to null to avoid memory leaks
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}