package com.chethan.mercari.binding

import androidx.databinding.DataBindingComponent
import androidx.fragment.app.Fragment
import com.chethan.mercari.binding.FragmentBindingAdapters

/**
 * A Data Binding Component implementation for fragments.
 */
class FragmentDataBindingComponent(fragment: Fragment) : DataBindingComponent {

    private val adapter = FragmentBindingAdapters(fragment)

    override fun getFragmentBindingAdapters() = adapter
}
