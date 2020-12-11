package com.ddona.mvvm.extension

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.data.DataFetcher
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoader.LoadData
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import com.bumptech.glide.signature.ObjectKey


data class PackageModel(var packageName: String = "", var type: Int = 0)

// for drawable
class DrawableDataFetcher(private val context: Context, private val model: PackageModel) : DataFetcher<Drawable?> {

    override fun loadData(priority: Priority, callback: DataFetcher.DataCallback<in Drawable?>) {
        try {
            val info = context.packageManager.getApplicationInfo(model.packageName, PackageManager.GET_META_DATA)
            val icon = context.packageManager.getApplicationIcon(info)
            callback.onDataReady(icon)
        } catch (e: Exception) {
            e.printStackTrace()
            callback.onDataReady(null)
        }
    }

    override fun cleanup() { // Empty Implementation
    }

    override fun cancel() { // Empty Implementation
    }

    override fun getDataClass() = Drawable::class.java as Class<Drawable?>

    override fun getDataSource() = DataSource.LOCAL

}

class DrawableModelLoader(private val context: Context) : ModelLoader<PackageModel, Drawable> {

    override fun buildLoadData(model: PackageModel, width: Int, height: Int, options: Options) =
        LoadData<Drawable>(ObjectKey(model), DrawableDataFetcher(context, model))

    override fun handles(model: PackageModel) = true

}

class DrawableModelLoaderFactory(private val mContext: Context) : ModelLoaderFactory<PackageModel, Drawable> {

    override fun build(multiFactory: MultiModelLoaderFactory) = DrawableModelLoader(mContext)

    override fun teardown() { // Empty Implementation.
    }

}