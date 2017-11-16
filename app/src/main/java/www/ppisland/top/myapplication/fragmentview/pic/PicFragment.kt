package com.yhao.module.pic

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import kotlinx.android.synthetic.main.fragment_pic.*
import www.ppisland.top.myapplication.R
import www.ppisland.top.myapplication.commen.base.BaseFragment

/**
 * Created by yhao on 17-9-5.
 *
 */


val tabs: Array<String> = arrayOf("大胸妹", "小清新", "文艺范", "性感妹", "大长腿", "黑丝袜", "小翘臀")
val types: Array<Int> = arrayOf(34, 35, 36, 37, 38, 39, 40)


class PicFragment : BaseFragment() {
    override fun initView() {
    }

    override fun initEvent() {
    }

    override fun loadData() {
        mViewPager.offscreenPageLimit = tabs.size
        mViewPager.adapter = PicPageAdapter(childFragmentManager)
        mTabLayout.setupWithViewPager(mViewPager)
    }

    override fun setContentView(): Int {
        return R.layout.fragment_pic
    }


    private inner class PicPageAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return ClassifyPicFragment.newInstance(types[position])
        }

        override fun getPageTitle(position: Int): CharSequence {
            return tabs[position]
        }

        override fun getCount(): Int {
            return tabs.size
        }
    }

}
