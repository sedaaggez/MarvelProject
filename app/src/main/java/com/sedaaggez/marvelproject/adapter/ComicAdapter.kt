package com.sedaaggez.marvelproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sedaaggez.marvelproject.R
import com.sedaaggez.marvelproject.databinding.ItemComicBinding
import com.sedaaggez.marvelproject.model.comic.ComicResult

class ComicAdapter(val resultList: ArrayList<ComicResult>) :
    RecyclerView.Adapter<ComicAdapter.ComicViewHolder>() {

    class ComicViewHolder(var view: ItemComicBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<ItemComicBinding>(inflater, R.layout.item_comic, parent, false)
        return ComicViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        holder.view.comicResult = resultList[position]
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    fun updateComicsList(newItemList: List<ComicResult>) {
        resultList.clear()
        resultList.addAll(newItemList)
        notifyDataSetChanged()
    }
}