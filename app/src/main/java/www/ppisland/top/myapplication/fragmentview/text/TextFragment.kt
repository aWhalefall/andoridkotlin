package www.ppisland.top.myapplication.fragmentview.text

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.yhao.module.pic.RhesisFragment
import kotlinx.android.synthetic.main.fragment_pic.*
import www.ppisland.top.myapplication.R
import www.ppisland.top.myapplication.commen.base.BaseFragment
import www.ppisland.top.myapplication.fragmentview.text.joke.JokeFragment

/**
 * Created by weichyang on 2017/11/1.
 */
class TextFragment : BaseFragment() {


    val tabs: Array<String> = arrayOf("搞笑段子", "励志鸡汤")

    override fun setContentView(): Int {
        return R.layout.fragment_pic
    }

    override fun initView() {
    }

    override fun initEvent() {
    }

    override fun loadData() {
        mViewPager.offscreenPageLimit = tabs.size
        mViewPager.adapter = TextPageAdapter(childFragmentManager)
        mTabLayout.setupWithViewPager(mViewPager)
    }


    private inner class TextPageAdapter(fragmentM: FragmentManager) : FragmentStatePagerAdapter(fragmentM) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> JokeFragment()
                1 -> RhesisFragment()
                else -> JokeFragment()
            }
        }

        override fun getPageTitle(position: Int): CharSequence {
            return tabs[position]
        }

        override fun getCount(): Int {
            return tabs.size
        }

    }


}