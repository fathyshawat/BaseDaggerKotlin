package com.example.daggerkotlin.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.daggerkotlin.model.articles.ArticlesItem
import com.squareup.picasso.Picasso

class BindingAdapter {


    companion object {
        @BindingAdapter("android:src")
        @JvmStatic
        fun setImageViewResource(imageView: ImageView, urlImage: String) {
            Picasso.get().load(urlImage).into(imageView)
        }
    }
}