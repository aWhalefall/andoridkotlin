package www.ppisland.top.myapplication.commen.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by weichyang on 2017/11/1.
 *
 */
abstract class BaseFragment : Fragment() {

    protected var currentActivity: Activity? = null //当前activity


    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        acceptBundle();
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        var viewResource=setContentView()
        return inflater!!.inflate(viewResource, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initEvent()
        loadData()
    }


    /**
     * 参数接收
     */
    fun acceptBundle(){

    }
    /**
     * view 初始化
     */
    abstract fun initView()
    /**
     * 事件初始化
     */
    abstract fun initEvent()

    /**
     * 数据初始化
     */
    abstract fun loadData()


    /**
     * 设置布局View
     */
    abstract fun setContentView(): Int


}