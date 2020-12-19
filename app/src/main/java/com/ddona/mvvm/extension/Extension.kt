package com.ddona.mvvm.extension

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ddona.mvvm.R
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

fun Context.getColorAttr(attr: Int): Int {
    val typedArray = obtainStyledAttributes(intArrayOf(attr))
    val color = typedArray.getColor(0, Color.LTGRAY)
    typedArray.recycle()
    return color
}

fun Context.showShortToast(content: String) {
    Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
}

fun Context.showLongToast(content: String) {
    Toast.makeText(this, content, Toast.LENGTH_LONG).show()
}

fun ViewGroup.inflater(@LayoutRes id: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(this.context).inflate(id, this, attachToRoot)
}

fun ImageView.loadImage(link: String) {
    Glide.with(this).load(link).placeholder(R.drawable.pokemon).into(this)
}

fun Long.convertTimestampToDate(): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    return dateFormat.format(Date(this))
}

fun Long.convertAmountTimeToText(): String {
    return String.format(
        "%02d:%02d",
        TimeUnit.MILLISECONDS.toMinutes(this),
        TimeUnit.MILLISECONDS.toSeconds(this) - TimeUnit.MINUTES.toSeconds(
            TimeUnit.MILLISECONDS.toMinutes(this)
        )
    )
}

fun RecyclerView.setupSwipeItem(
    dragDirs: Int,
    swipeDirs: Int,
    dragAction: (Int) -> Boolean,
    swipeAction: (Int) -> Unit
) {
    val simpleCallback = object : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return dragAction.invoke(viewHolder.absoluteAdapterPosition)
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            swipeAction.invoke(viewHolder.absoluteAdapterPosition)
        }
    }
    ItemTouchHelper(simpleCallback).attachToRecyclerView(this)
}

@BindingAdapter("link")
fun ImageView.setResourceByLink(link: String) {
    Glide.with(this).load(link).placeholder(R.mipmap.ic_launcher).into(this)
}