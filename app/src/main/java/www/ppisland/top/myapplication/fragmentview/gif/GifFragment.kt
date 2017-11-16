package www.ppisland.top.myapplication.fragmentview.gif

import android.support.v7.widget.LinearLayoutManager
import android.view.ViewGroup
import com.yhao.model.bean.Gif
import kotlinx.android.synthetic.main.fragment_gif.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import www.ppisland.top.myapplication.R
import www.ppisland.top.myapplication.commen.base.BaseFragment
import www.ppisland.top.myapplication.service.GifService
import www.ppisland.top.myapplication.showSnackbar
import kotlin.properties.Delegates

/**
 * Created by weichyang on 2017/10/31.
 */
class GifFragment : BaseFragment() {


    private var mData: MutableList<Gif> = ArrayList()

    var mPage: Int = 1

    private var mLoading by Delegates.observable(true) {
        _, _, new ->
        mSwipeRefreshLayout.isRefreshing = new
    }


    override fun setContentView(): Int {
       return R.layout.fragment_gif
    }


    override fun initView() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.cardview_shadow_end_color)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
    }

    override fun initEvent() {
        mSwipeRefreshLayout.setOnRefreshListener {
            mPage = 1
            loadData()
        }
        mRecyclerView.setOnTouchListener { _, _ ->
            if (!mLoading && !mRecyclerView.canScrollVertically(1)) {
                mPage++
                loadData()
            }
            false
        }
    }


    override fun loadData() {
        mLoading = true
        doAsync {
            val data = GifService.getData(mPage)
            uiThread {
                mLoading=false
                if (data == null) {
                    showSnackbar(view as ViewGroup, "加载失败")
                    return@uiThread
                }
                if (mRecyclerView.adapter == null) {
                    mData.addAll(data)
                    initAdapter()
                } else if (mPage > 1) {
                    val pos = mData.size
                    mData.addAll(data)
                    mRecyclerView.adapter.notifyItemRangeInserted(pos, data.size)
                } else {
                    mData.clear()
                    mData.addAll(data)
                    mRecyclerView.adapter.notifyDataSetChanged()
                }

            }
        }


    }

    private fun initAdapter() {
        mRecyclerView.adapter = GifAdapter(mData, mRecyclerView);
    }

    public fun pauseGif() {
        if (mRecyclerView != null && mRecyclerView.adapter != null) {
            (mRecyclerView.adapter as GifAdapter).pauseGif()
        }
    }
}