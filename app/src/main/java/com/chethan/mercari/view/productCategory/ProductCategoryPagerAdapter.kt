package com.chethan.mercari.view.productCategory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.chethan.mercari.model.ProductCategory
import com.chethan.mercari.view.Products.ProductsFragment


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class ProductCategoryPagerAdapter(
    private val productCategoryList: List<ProductCategory>,
    fm: Fragment
) :
    FragmentStateAdapter(fm) {

    override fun getItemCount(): Int = productCategoryList.size

    override fun createFragment(position: Int): Fragment =
        ProductsFragment.newInstance(productCategoryList[position])
}