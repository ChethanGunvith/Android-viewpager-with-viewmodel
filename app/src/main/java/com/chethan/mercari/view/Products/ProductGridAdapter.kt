package com.chethan.mercari.view.Products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.chethan.mercari.AppExecutors
import com.chethan.mercari.common.DataBoundListAdapter
import com.chethan.mercari.databinding.ProductEntryBinding
import com.chethan.mercari.model.ProductOverview


/**
 * Created by Chethan on 7/28/2019.
 */

class ProductGridAdapter(
    appExecutors: AppExecutors,
) : DataBoundListAdapter<ProductOverview, ProductEntryBinding>(
    appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<ProductOverview>() {
        override fun areItemsTheSame(
            oldItem: ProductOverview,
            newItem: ProductOverview
        ) = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: ProductOverview,
            newItem: ProductOverview
        ) = oldItem == newItem
    }
) {

    override fun createBinding(parent: ViewGroup): ProductEntryBinding =
        ProductEntryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

    override fun bind(
        binding: ProductEntryBinding,
        item: ProductOverview
    ) {
        binding.productOverview = item

    }
}
