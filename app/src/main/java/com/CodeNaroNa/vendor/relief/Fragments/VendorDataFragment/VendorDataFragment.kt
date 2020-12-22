package com.CodeNaroNa.vendor.relief.Fragments.VendorDataFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.CodeNaroNa.vendor.relief.GlobalHelpers.Constants
import com.CodeNaroNa.vendor.relief.GlobalHelpers.Resource
import com.CodeNaroNa.vendor.relief.MainActivity.ViewModels.MainActivityViewModel
import com.CodeNaroNa.vendor.relief.VendorActivity.ViewModels.VendorActivityViewModel
import com.CodeNaroNa.vendor.relief.databinding.FragmentVendorDataBinding
import com.CodeNaroNa.vendor.relief.Models.UserData


class VendorDataFragment : Fragment() {


    companion object {
        /**
         * This method is used to obtain an object of VendorDataFragment along with the bundle argument of fromClass which
         * specifies from which Activity the fragment is obtained
         *
         * This is done for reusing of code since 2 Activities Are using the same fragment we can specify the calling Activity class
         * so as to perform operations accordingly
         *
         * @param fromClass -> A tag used to specify the calling activity
         * @return VendorDataFragment with calling class tag bundle argument
         */
        @JvmStatic
        fun newInstance(fromClass: String) = VendorDataFragment().apply {
            arguments = Bundle().apply {
                putString(Constants.FROM_CLASS_KEY, fromClass)
            }
        }
    }

    private var _binding: FragmentVendorDataBinding? = null
    private val binding get() = _binding!!
    private var fromClass: String = ""

    private lateinit var sharedViewModel: MainActivityViewModel
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
        // Inflate the layout for this fragment
        _binding = FragmentVendorDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialiseView()
    }

    private fun initialiseView()
    {
        if(fromClass == Constants.FROM_DEFAULT_CLASS)
        {
            fillData(sharedViewModel.selectedUserData)
        }else
        {
            sharedViewModel2.vendor.observe(viewLifecycleOwner,{
                when(it)
                {
                    is Resource.Success->{
                        fillData(it.data!!)
                    }
                    else ->{}
                }
            })
        }


        /**
         *  [Bug-Fix]Don't remove this empty onClickLister,
         *  if removed the onClick events will be sent to back Stack fragments thus opening the same fragment
         *  when user click on the view
         */
        binding.root.setOnClickListener {
        }
    }

    private fun fillData(data: UserData)
    {
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
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}