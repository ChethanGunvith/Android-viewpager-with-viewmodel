package com.chethan.mercari.view.Products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.chethan.mercari.databinding.CategoryProductsBinding
import com.chethan.mercari.model.ProductCategory
import com.chethan.mercari.testing.OpenForTesting
import com.chethan.mercari.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
@OpenForTesting
@AndroidEntryPoint
class ProductsFragment : Fragment() {

    private val productsViewModel: ProductsViewModel by viewModels()

    var binding by autoCleared<CategoryProductsBinding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            CategoryProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val categoryUrl = arguments?.getString(CATEGORY_URL) ?: ""
        val categoryName = arguments?.getString(CATEGORY_NAME) ?: ""
        productsViewModel.getData(categoryUrl, categoryName)
        binding.lifecycleOwner = viewLifecycleOwner
        productsViewModel.products.observe(viewLifecycleOwner) { result ->

            if (result.data != null)
                context?.let {
                    if (result.data.isNotEmpty()) {
                        val productGridAdapter = ProductGridAdapter(it, result.data)
                        binding.productsGridView.adapter = productGridAdapter
                    }
                }
        }


    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val CATEGORY_NAME = "category_name"
        private const val CATEGORY_URL = "category_url"


        @JvmStatic
        fun newInstance(productCategory: ProductCategory): ProductsFragment {
            return ProductsFragment().apply {
                arguments = Bundle().apply {
                    putString(CATEGORY_NAME, productCategory.name.toString())
                    putString(CATEGORY_URL, productCategory.data.toString())
                }
            }
        }
    }
}