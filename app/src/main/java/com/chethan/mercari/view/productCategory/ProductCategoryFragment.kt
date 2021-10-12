package com.chethan.mercari.view.productCategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.chethan.mercari.databinding.ProductCategoryFragmentBinding
import com.chethan.mercari.repository.Status
import com.chethan.mercari.testing.OpenForTesting
import com.chethan.mercari.utils.autoCleared
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Chethan on 7/30/2019.
 */

@OpenForTesting
@AndroidEntryPoint
class ProductCategoryFragment : Fragment() {
    private val productCategoryViewModel: ProductCategoryViewModel by viewModels()

    var binding by autoCleared<ProductCategoryFragmentBinding>()

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
            result.data?.let {
                if (it.isNotEmpty()) {
                    binding.productCategoryViewPager.adapter =
                        ProductCategoryPagerAdapter(result.data, this)
                    TabLayoutMediator(
                        binding.tabs,
                        binding.productCategoryViewPager
                    ) { tab, position ->
                        tab.text = it[position].name
                    }.attach()
                }
            }
        }


    }
}