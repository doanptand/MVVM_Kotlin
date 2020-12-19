package com.ddona.mvvm.ui.sample

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ddona.mvvm.R
import com.ddona.mvvm.adapter.SampleAdapter
import com.ddona.mvvm.databinding.ActivitySampleBinding
import com.ddona.mvvm.extension.showLongToast
import com.ddona.mvvm.extension.showShortToast
import com.ddona.mvvm.util.Result
import com.ddona.mvvm.util.Status
import com.ddona.mvvm.viewmodel.SampleViewModel
import com.ddona.mvvm.widget.addDivider
import kotlinx.android.synthetic.main.activity_sample.*

class SampleActivity : AppCompatActivity() {
    private val viewModel: SampleViewModel by viewModels()
    private val mAdapter by lazy {
        SampleAdapter()
    }
    private lateinit var binding: ActivitySampleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sample)
        binding.name = "Hello world"
        binding.link =
            "https://image.thanhnien.vn/768/uploaded/ngocthanh/2020_07_13/ngoctrinhmuonsinhcon1_swej.jpg"
        binding.executePendingBindings()
        setupToolbar()
        initRecyclerView()
        subscribeUI()
    }

    private fun initRecyclerView() {
        recyclerView.addDivider(
            insetRight = R.dimen.divider_show_view_inset,
            insetLeft = R.dimen.divider_show_view_inset
        )
        recyclerView.adapter = mAdapter
        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = false
        }
    }

    private fun subscribeUI() {
        viewModel.getData().observe(this) {
            when (it.status) {
                Status.LOADING -> {
                    txt_no_history.visibility = View.VISIBLE
                    swipeRefresh.visibility = View.GONE
                    layout_list_history.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    txt_no_history.visibility = View.GONE
                    swipeRefresh.visibility = View.VISIBLE
                    layout_list_history.visibility = View.VISIBLE
                    mAdapter.submitList(it.data)
                }
                Status.ERROR -> {
                    it.message?.let { msg -> showShortToast(msg) }
                }
            }
        }

        viewModel.getLocalData().observe(this, {
            when (it) {
                is Result.Success -> {
                    showLongToast("I get ${(it.data as List<*>).size} item")
                }
                is Result.Error -> {
                    showLongToast("The error happen ${it.e.message}")
                }
                is Result.Loading -> {
                    showLongToast("I'm trying to load data for you")
                }
            }
        })
    }

    private fun setupToolbar() {

    }

}