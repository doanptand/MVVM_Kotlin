package com.ddona.mvvm.extension

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.LayoutRes
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

fun ImageView.loadImage(link : String) {
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