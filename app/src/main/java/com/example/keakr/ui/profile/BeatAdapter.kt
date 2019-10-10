package com.example.keakr.ui.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.keakr.R
import com.example.keakr.data.model.Beat
import com.example.keakr.data.model.User
import kotlinx.android.synthetic.main.beat_list_item_layout.view.*
import kotlinx.android.synthetic.main.header_item_layout.view.*
import kotlinx.android.synthetic.main.profile_fragment.*
import kotlin.collections.ArrayList

class BeatAdapter(val context: Context, private val showHeader : Boolean, val listener: ProfileFragment.OnSeeMoreClickedListener?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    constructor(
        context: Context,
        showHeader: Boolean
    ) : this(context, showHeader, null)

    private var items : ArrayList<Beat> = ArrayList()
    private var user = User()

    override fun getItemViewType(position: Int): Int {
        return if (position == 0 && showHeader)
            0
        else
            1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            0 -> HeaderViewHolder(
                LayoutInflater.from(context)
                    .inflate(R.layout.header_item_layout, parent, false), context)
            else -> {
                BeatViewHolder(
                    LayoutInflater.from(context)
                        .inflate(R.layout.beat_list_item_layout, parent, false), context)
            }
        }

    }

    override fun getItemCount(): Int {
       return if (showHeader) items.size + 1 else items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderViewHolder){
            (holder).bind(user, listener)
        } else {
            (holder as BeatViewHolder).bind(items[ if (showHeader) holder.adapterPosition - 1 else holder.adapterPosition])
        }
    }

    fun setUser(user: User){
        this.user = user
        notifyItemChanged(0)
    }

    fun add(beat : Beat){
        items.add(beat)
        notifyItemInserted(items.size - 1)
    }

    fun addItems(beats : List<Beat>){
        for (beat : Beat in beats){
            add(beat)
        }
    }

}

class HeaderViewHolder(v: View, private val context: Context) : RecyclerView.ViewHolder(v){

    fun bind(
        user: User,
        listener: ProfileFragment.OnSeeMoreClickedListener?
    ){
        itemView.username.text = user.username
        itemView.bio.text = user.biography
        Glide.with(context)
            .load(user.pictureUrl)
            .into(itemView.picture)
        itemView.seemore.setOnClickListener {
            listener?.onSeeMoreClicked(ArrayList())
        }
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
        val llm = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        itemView.list.layoutManager = llm
        val genres = ArrayList<String>()
        if (beat.genres?.size!! > 2 )
            genres.addAll(beat.genres?.subList(0,2))
        else
            genres.addAll(beat.genres)
        var genreAdapter = GenreAdapter(context, genres)
        itemView.list.adapter = genreAdapter
    }
}