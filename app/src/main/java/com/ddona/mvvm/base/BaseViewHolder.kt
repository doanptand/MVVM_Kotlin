package com.ddona.mvvm.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ddona.mvvm.widget.InsetDividerDecoration

abstract class BaseViewHolder<T>(view: View) : RecyclerView.ViewHolder(view),
    InsetDividerDecoration.HasDivider {
    abstract fun bind(item: T)
    override fun canDivide(): Boolean = true
    open fun onDetached() = Unit
}