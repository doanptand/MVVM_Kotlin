package com.ddona.mvvm.widget.glide

import android.content.Context
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.ddona.mvvm.extension.DrawableModelLoaderFactory
import com.ddona.mvvm.extension.PackageModel

@GlideModule
class GlideModule : AppGlideModule() {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        registry.prepend(
            PackageModel::class.java, Drawable::class.java,
            DrawableModelLoaderFactory(context)
        )
    }

}