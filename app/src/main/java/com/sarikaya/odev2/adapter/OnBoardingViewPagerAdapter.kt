package com.sarikaya.odev2.adapter

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.sarikaya.odev2.R
import com.sarikaya.odev2.model.OnBoardingData

class OnBoardingViewPagerAdapter(private var context : Context, private var onBoardingDataList: List<OnBoardingData>) : PagerAdapter() {
    override fun getCount(): Int {
        return onBoardingDataList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as  View)

    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.onboarding_screen_layout, null);
        val imageView: ImageView
        val title: TextView
        val description: TextView

        imageView = view.findViewById(R.id.imageView)
        title = view.findViewById(R.id.title)
        description = view.findViewById(R.id.description)

        imageView.setImageResource(onBoardingDataList[position].imageUrl)
        title.text = onBoardingDataList[position].title
        description.text = onBoardingDataList[position].description

        container.addView(view)
        return view
    }
}