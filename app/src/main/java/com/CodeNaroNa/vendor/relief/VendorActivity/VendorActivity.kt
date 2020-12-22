package com.CodeNaroNa.vendor.relief.VendorActivity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.CodeNaroNa.vendor.relief.Fragments.VendorFragment.VendorFragment
import com.CodeNaroNa.vendor.relief.GlobalHelpers.Utility
import com.CodeNaroNa.vendor.relief.R
import com.CodeNaroNa.vendor.relief.Repositories.CentralRepository
import com.CodeNaroNa.vendor.relief.VendorActivity.ViewModels.VendorActivityViewModel
import com.CodeNaroNa.vendor.relief.VendorActivity.ViewModels.VendorActivityViewModelFactory
import com.CodeNaroNa.vendor.relief.GlobalHelpers.BaseActivity
import com.CodeNaroNa.vendor.relief.databinding.ActivityVendor2Binding

class VendorActivity : BaseActivity()
{
    private lateinit var binding: ActivityVendor2Binding
    private lateinit var viewModel: VendorActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVendor2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //ViewModel Creation [Do not remove]
        val factory = VendorActivityViewModelFactory(this.applicationContext, CentralRepository())
        viewModel = ViewModelProvider(this,factory).get(VendorActivityViewModel::class.java)

        Utility.navigateFragment(supportFragmentManager, R.id.vendorMainContainer, VendorFragment(),"vendorFrag",replaceOrAdd = false,addToBackStack = false)
    }
}