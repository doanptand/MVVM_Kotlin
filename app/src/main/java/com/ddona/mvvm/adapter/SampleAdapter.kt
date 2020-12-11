package com.ddona.mvvm.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.ddona.mvvm.R
import com.ddona.mvvm.base.BaseListAdapter
import com.ddona.mvvm.base.BaseViewHolder
import com.ddona.mvvm.extension.inflater
import kotlinx.android.synthetic.main.simple_list_item_1.view.*

class SampleAdapter : BaseListAdapter<String?>(String.DIFF_CALLBACK) {
    override fun contentViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<String?> =
        SampleViewHolder(parent)

    inner class SampleViewHolder(parent: ViewGroup) :
        BaseViewHolder<String?>(parent.inflater(R.layout.simple_list_item_1)) {
        override fun bind(item: String?) {
            itemView.text1.text = item
        }

    }


}

private val String.Companion.DIFF_CALLBACK: DiffUtil.ItemCallback<String?>
    get() = object : DiffUtil.ItemCallback<String?>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem
    }

