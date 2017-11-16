package com.yhao.module.pic

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yhao.commen.notNullSingleValue
import com.yhao.model.service.HuabanService
import kotlinx.android.synthetic.main.fragment_pic_classify.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import www.ppisland.top.myapplication.BigPicActivity
import www.ppisland.top.myapplication.R
import www.ppisland.top.myapplication.model.Huaban
import java.util.*
import kotlin.properties.Delegates

/**
 * Created by yhao on 17-9-5.
 *
 */

class ClassifyPicFragment : Fragment() {

    private var mType: Int by notNullSingleValue()
    private var mData: MutableList<Huaban> = ArrayList()
    private var mPage: Int = 1
    private var mLoading by Delegates.observable(true) { _, _, new ->
        mSwipeRefreshLayout_classfy.isRefreshing = new
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_pic_classify, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mType = arguments.getInt(EXTRA_TYPE)
        initView()
        initEvent()
        loadData()
    }


    private fun initView() {
        mSwipeRefreshLayout_classfy.setColorSchemeResources(R.color.colorPrimary)
        mRecyclerView_classfy.setHasFixedSize(true)
        mRecyclerView_classfy.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        (mRecyclerView_classfy.layoutManager as StaggeredGridLayoutManager).gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
    }

    private fun initEvent() {
        mSwipeRefreshLayout_classfy.setOnRefreshListener {
            mPage = 1
            loadData()
        }
        mRecyclerView_classfy.setOnTouchListener { _, _ ->
            if (!mLoading && !mRecyclerView_classfy.canScrollVertically(1)) {
                mPage++
                loadData()
            }
            false
        }
    }

    private fun loadData() {
        mLoading = true
        doAsync {
            val data = HuabanService.getData(mType, mPage)
            uiThread {
                mLoading = false
                if (data == null) {
                    //showSnackbar(view as ViewGroup, "加载失败")
                    return@uiThread
                }
                if (mRecyclerView_classfy.adapter == null) {
                    mData.addAll(data)
                    initAdapter()
                } else if (mPage > 1) {
                    val pos = mData.size
                    mData.addAll(data)
                    mRecyclerView_classfy.adapter.notifyItemRangeInserted(pos, data.size)
                } else {
                    mData.clear()
                    mData.addAll(data)
                    mRecyclerView_classfy.adapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun initAdapter() {
        mRecyclerView_classfy.adapter = PicAdapter(mData)
        (mRecyclerView_classfy.adapter as PicAdapter).setOnItemCLick(object : PicAdapter.OnItemClickListener {
            override fun onClick(view: View, url: String) {
                BigPicActivity.launch(this@ClassifyPicFragment.activity as AppCompatActivity, view, url)
            }
        })

    }


    companion object {
        private val EXTRA_TYPE: String = "extra_type"
        fun newInstance(type: Int): ClassifyPicFragment {
            val mFragment = ClassifyPicFragment()
            val bundle = Bundle()
            bundle.putInt(EXTRA_TYPE, type)
            mFragment.arguments = bundle
            return mFragment
        }
    }

}
