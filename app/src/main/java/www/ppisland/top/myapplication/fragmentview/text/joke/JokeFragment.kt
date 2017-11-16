package www.ppisland.top.myapplication.fragmentview.text.joke

import android.support.v7.widget.LinearLayoutManager
import android.view.ViewGroup
import com.yhao.model.bean.Joke
import kotlinx.android.synthetic.main.fragment_pic_classify.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import www.ppisland.top.myapplication.R
import www.ppisland.top.myapplication.commen.base.BaseFragment
import www.ppisland.top.myapplication.service.JokeService
import www.ppisland.top.myapplication.showSnackbar
import kotlin.properties.Delegates

/**
 * Created by weichyang on 2017/11/1.
 */
class JokeFragment:BaseFragment(){

    private var mPage: Int=1
    private var mData: MutableList<Joke> = ArrayList()


    private var mLoading by Delegates.observable(true) {
        _, _, new ->
        mSwipeRefreshLayout_classfy.isRefreshing = new
    }

    override fun setContentView(): Int {
        return R.layout.fragment_pic_classify;
    }


    override fun initView() {
        mSwipeRefreshLayout_classfy.setColorSchemeResources(R.color.colorPrimary)
        mRecyclerView_classfy.setHasFixedSize(true)
        mRecyclerView_classfy.layoutManager = LinearLayoutManager(context)
    }



    override fun initEvent() {
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

    override fun loadData() {
            mLoading = true
            doAsync {
                val data = JokeService.getData(mPage)
                uiThread {
                    mLoading = false
                    if (data == null) {
                        showSnackbar(view as ViewGroup, "加载失败")
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
        mRecyclerView_classfy.adapter = JokeAdapter(mData)
    }

}