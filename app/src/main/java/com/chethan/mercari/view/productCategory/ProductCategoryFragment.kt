package com.chethan.mercari.view.productCategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.chethan.mercari.api.binding.FragmentDataBindingComponent
import com.chethan.mercari.databinding.ProductCategoryFragmentBinding
import com.chethan.mercari.testing.OpenForTesting
import com.chethan.mercari.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Created by Chethan on 7/30/2019.
 */

@OpenForTesting
@AndroidEntryPoint
class ProductCategoryFragment : Fragment() {
    private val productCategoryViewModel: ProductCategoryViewModel by viewModels()

    var binding by autoCleared<ProductCategoryFragmentBinding>()
    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProductCategoryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.productCategory = productCategoryViewModel.productCategories
        productCategoryViewModel.productCategories.observe(viewLifecycleOwner) { result ->
            if (result.data != null)
                context?.let {
                    if (result.data.isNotEmpty()) {
                        val sectionsPagerAdapter =
                            ProductCategoryPagerAdapter(result.data, childFragmentManager)
                        binding.productCategoryViewPager.adapter = sectionsPagerAdapter
                        binding.tabs.setupWithViewPager(binding.productCategoryViewPager)
                    }
                }

        }
    }

    /**
     * Created to be able to override in tests
     */
    fun navController() = findNavController()
}