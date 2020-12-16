package com.demoapp.utills


import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("recyclerViewAdapter", "spanCount", "orientation", "snapIt")
fun <T : RecyclerView.ViewHolder> setRecyclerViewAdapter(
    view: RecyclerView, adapter: RecyclerView.Adapter<T>, spanCount: Int,
    orientation: Int, snapIt: Boolean
) {

    view.overScrollMode = View.OVER_SCROLL_NEVER
    val gridLayoutManager = GridLayoutManager(view.context, spanCount, orientation, false)
    gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
            return if (position == 0 && view.adapter?.getItemViewType(0) == 0) gridLayoutManager.spanCount
            else 1
        }
    }
    view.adapter = adapter
    view.layoutManager = gridLayoutManager
    if (snapIt) {
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(view)
    }

}



@BindingAdapter("app:glideImageRound")
fun setImageRound(view: AppCompatImageView, path: String) {

    Glide.with(view)
        .load(path)
        .apply(RequestOptions.circleCropTransform())
        .into(view)


}


@BindingAdapter("app:glideImage")
fun setImage(view: AppCompatImageView, path: String) {

    Glide.with(view)
        .load(path)
        .into(view)


}