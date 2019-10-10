package com.example.keakr.ui.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.keakr.R
import kotlinx.android.synthetic.main.beat_genre_list_item_layout.view.*

class GenreAdapter(val context: Context, val items : ArrayList<String>)
    : RecyclerView.Adapter<GenreViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.beat_genre_list_item_layout, parent, false))
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(items[position])
    }

}

class GenreViewHolder(v : View) : RecyclerView.ViewHolder(v){
    fun bind(string: String){
        itemView.genre.text = string
    }
}
