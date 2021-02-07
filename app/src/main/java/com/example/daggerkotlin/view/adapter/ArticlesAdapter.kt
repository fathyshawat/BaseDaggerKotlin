package com.example.daggerkotlin.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.daggerkotlin.R
import com.example.daggerkotlin.databinding.ItemArticlesBinding
import com.example.daggerkotlin.model.articles.ArticlesItem

class ArticlesAdapter(
    private val articles: ArrayList<ArticlesItem>?
) :
    RecyclerView.Adapter<ArticlesAdapter.ArticlesHolder>() {

    override fun getItemCount() = articles!!.size

    fun setArticles(items: ArrayList<ArticlesItem>?){
        articles!!.clear()
        articles.addAll(items!!)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ArticlesHolder(
            DataBindingUtil.inflate<ItemArticlesBinding>
                (
                LayoutInflater.from(parent.context),
                R.layout.item_articles,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ArticlesHolder, position: Int) {
        holder.recyclerviewArticlesBinding.data = articles!![position]
    }

    inner class ArticlesHolder(val recyclerviewArticlesBinding: ItemArticlesBinding) :
        RecyclerView.ViewHolder(recyclerviewArticlesBinding.root) {
    }
}