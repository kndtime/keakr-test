package com.example.keakr.ui.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.keakr.R
import com.example.keakr.data.model.Beat
import kotlinx.android.synthetic.main.beat_list_item_layout.view.*

class BeatAdapter(val context: Context) : RecyclerView.Adapter<BeatViewHolder>(){

    private var items : ArrayList<Beat> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeatViewHolder {
       return BeatViewHolder(
           LayoutInflater.from(context)
               .inflate(R.layout.beat_list_item_layout, parent, false), context)
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: BeatViewHolder, position: Int) {
        holder.bind(items[holder.adapterPosition])
    }

}


class BeatViewHolder(v: View, private val context: Context) : RecyclerView.ViewHolder(v){

    fun bind(beat: Beat){
        itemView.artist.text = beat.artist
        itemView.stream.text = "${beat.stats?.listeningCount} streams"
        itemView.name.text =  beat.title
        Glide.with(context)
            .load(beat.posterUrl)
            .fitCenter()
            .into(itemView.image)
    }
}