package com.CodeNaroNa.vendor.relief.Fragments.VendorFragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.CodeNaroNa.vendor.relief.Fragments.CoronaSpreadDataFragment.CoronaSpreadDataFragment
import com.CodeNaroNa.vendor.relief.Fragments.PrecautionFragment.PrecautionFragment
import com.CodeNaroNa.vendor.relief.Fragments.VendorDataFragment.VendorDataFragment

import com.CodeNaroNa.vendor.relief.GlobalHelpers.Constants
import com.CodeNaroNa.vendor.relief.GlobalHelpers.Resource
import com.CodeNaroNa.vendor.relief.GlobalHelpers.Utility
import com.CodeNaroNa.vendor.relief.R
import com.CodeNaroNa.vendor.relief.VendorActivity.ViewModels.VendorActivityViewModel
import com.CodeNaroNa.vendor.relief.GlobalHelpers.BaseActivity
import com.CodeNaroNa.vendor.relief.MainActivity.MainActivity
import com.CodeNaroNa.vendor.relief.databinding.FragmentVendorBinding
import com.google.firebase.auth.FirebaseAuth


class VendorFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private var _binding: FragmentVendorBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedViewModel: VendorActivityViewModel
    private lateinit var baseActivity : BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseActivity = requireActivity() as BaseActivity
        sharedViewModel = ViewModelProvider(requireActivity()).get(VendorActivityViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentVendorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialiseView()
        setUpClickListeners()
        setUpLiveDataObservers()
    }

    private fun setUpClickListeners() {
        binding.vendorBottomNav.signOut.setOnClickListener {
            signOut()
        }

        binding.vendorBottomNav.showData.setOnClickListener {
            showData()
        }

        binding.vendorBottomNav.prec.setOnClickListener {
            showPrecautionFragment()
        }

        binding.vendorBottomNav.dataB.setOnClickListener {
            showCoronaSpreadData()
        }

        binding.saveFrag.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        sharedViewModel.saveData(binding.shopNameFrag.text.toString().trim(),
                binding.shopCategoryFrag.selectedItem.toString(),binding.stateFrag.selectedItem?.toString()?:"",
                binding.cityFrag.selectedItem?.toString()?:"",binding.addressFrag.text.toString().trim(),
                binding.openingFrag.selectedItem.toString()+" "+binding.openampmFrag.selectedItem.toString(),
                binding.closingFrag.selectedItem.toString()+" "+binding.closeampmFrag.selectedItem.toString())
    }

    private fun showCoronaSpreadData() {
        Utility.navigateFragment(requireActivity().supportFragmentManager,
                R.id.vendorMainContainer, CoronaSpreadDataFragment(),"coronaDataFrag")
    }

    private fun showPrecautionFragment() {
        Utility.navigateFragment(requireActivity().supportFragmentManager,
                R.id.vendorMainContainer, PrecautionFragment.newInstance(Constants.FROM_VENDOR_CLASS),"precautionDataFrag")
    }

    private fun showData() {
        sharedViewModel.getVendor(FirebaseAuth.getInstance().currentUser?.phoneNumber?:"")
        Utility.navigateFragment(requireActivity().supportFragmentManager,
                R.id.vendorMainContainer, VendorDataFragment.newInstance(Constants.FROM_VENDOR_CLASS),"vendorDataFrag")
    }

    private fun signOut() {
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(requireActivity(), MainActivity::class.java))
        requireActivity().finish()
    }

    private fun initialiseView() {
        binding.shopCategoryFrag.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, sharedViewModel.shopCategoryList)

        binding.openingFrag.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, sharedViewModel.timeList)
        binding.closingFrag.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, sharedViewModel.timeList)

        binding.openampmFrag.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, sharedViewModel.amPmList)
        binding.closeampmFrag.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, sharedViewModel.amPmList)

        binding.stateFrag.onItemSelectedListener = this
    }

    private fun setUpLiveDataObservers() {
        sharedViewModel.stateList.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    val stateArrayAdapter =
                            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, it.data!!)
                    binding.stateFrag.adapter = stateArrayAdapter
                }
                else -> {
                }
            }
        })

        sharedViewModel.cityList.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    val cityArrayAdapter =
                            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, it.data!!)
                    binding.cityFrag.adapter = cityArrayAdapter
                }
                else -> {
                }
            }
        })

        sharedViewModel.saveDataState.observe(viewLifecycleOwner,{
            when(it)
            {
                is Resource.Success ->{
                    baseActivity.hideProgressDialog()
                    baseActivity.showToast(it.data!!)
                }
                is Resource.Error -> {
                    baseActivity.hideProgressDialog()
                    baseActivity.showErrorSnackMessage(it.message!!)
                }
                is Resource.Loading -> {
                    baseActivity.showProgressDialog("Updating ...")
                }
                else->{}
            }
        })
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent?.id) {
            R.id.stateFrag -> {
                sharedViewModel.getCityList(parent.getItemAtPosition(position).toString())
                binding.cityFrag.adapter = null
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}