package com.chethan.mercari.view.Products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.chethan.mercari.AppExecutors
import com.chethan.mercari.databinding.CategoryProductsBinding
import com.chethan.mercari.model.ProductCategory
import com.chethan.mercari.repository.Status
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

    @Inject
    lateinit var appExecutors: AppExecutors

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            CategoryProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        val categoryUrl = arguments?.getString(CATEGORY_URL) ?: ""
        val categoryName = arguments?.getString(CATEGORY_NAME) ?: ""
        // Get data from remote / DB
        productsViewModel.getData(categoryUrl, categoryName)
        // Set adapter
        val productGridAdapter = ProductGridAdapter(appExecutors)
        binding.productsGridView.adapter = productGridAdapter
        productsViewModel.products.observe(viewLifecycleOwner) { result ->
            result.data?.let {
                productGridAdapter.submitList(it)
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

        fun newInstance(productCategory: ProductCategory): ProductsFragment {
            return ProductsFragment().apply {
                arguments = Bundle().apply {
                    putString(CATEGORY_NAME, productCategory.name)
                    putString(CATEGORY_URL, productCategory.data)
                }
            }
        }
    }
}