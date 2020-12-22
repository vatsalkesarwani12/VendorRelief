package com.CodeNaroNa.vendor.relief.MainActivity

import android.os.Bundle
import android.view.MenuItem
import com.CodeNaroNa.vendor.relief.Fragments.CoronaSpreadDataFragment.CoronaSpreadDataFragment
import com.CodeNaroNa.vendor.relief.Fragments.HomeFragment.HomeFragment
import com.CodeNaroNa.vendor.relief.Fragments.PrecautionFragment.PrecautionFragment
import com.CodeNaroNa.vendor.relief.GlobalHelpers.Utility
import com.CodeNaroNa.vendor.relief.R
import com.CodeNaroNa.vendor.relief.GlobalHelpers.BaseActivity
import com.CodeNaroNa.vendor.relief.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.bottomNavigation.setOnNavigationItemSelectedListener(this)

        Utility.navigateFragment(supportFragmentManager, R.id.fragment_layout, HomeFragment(), "homeFrag", addToBackStack = false, false)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                Utility.navigateFragment(supportFragmentManager, R.id.fragment_layout, HomeFragment(), "homeFrag", addToBackStack = false, false)
                return true
            }
            R.id.nav_data -> {
                Utility.navigateFragment(supportFragmentManager, R.id.fragment_layout, CoronaSpreadDataFragment(), "coronaFrag", addToBackStack = false, false)
                return true
            }
            R.id.nav_precaution -> {
                Utility.navigateFragment(supportFragmentManager, R.id.fragment_layout, PrecautionFragment(), "precautionFrag", addToBackStack = false, false)
                return true
            }
        }
        return false
    }
}