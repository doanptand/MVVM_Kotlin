package com.ddona.mvvm.ui.sample

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ddona.mvvm.R
import com.ddona.mvvm.adapter.SampleAdapter
import com.ddona.mvvm.widget.addDivider
import kotlinx.android.synthetic.main.activity_sample.*

class SampleActivity : AppCompatActivity() {
    private val viewModel: SampleViewModel by viewModels()
    private val mAdapter by lazy {
        SampleAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)
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
        viewModel.listHistory.observe(this) { history ->
            if (history.isNotEmpty()) {
                txt_no_history.visibility = View.GONE
                swipeRefresh.visibility = View.VISIBLE
                layout_list_history.visibility = View.VISIBLE
                mAdapter.submitList(history)
            } else {
                txt_no_history.visibility = View.VISIBLE
                swipeRefresh.visibility = View.GONE
                layout_list_history.visibility = View.GONE
            }
        }
    }

    private fun setupToolbar() {

    }

}