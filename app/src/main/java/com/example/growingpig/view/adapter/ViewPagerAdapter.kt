package com.example.growingpig.view.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.squareup.picasso.Picasso

class ViewPagerAdapter(context: Context, imageUrls: ArrayList<String>) : PagerAdapter() {

    private var context = context
    private var imageUrls = imageUrls


    override fun getCount(): Int {
        return imageUrls.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var imageView = ImageView(context)
        Picasso.get()
            .load(imageUrls[position])
            .fit()
            .centerCrop()
            .into(imageView)
        container.addView(imageView)


        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}