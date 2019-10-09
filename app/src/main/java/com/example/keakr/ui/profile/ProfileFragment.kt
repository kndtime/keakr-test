package com.example.keakr.ui.profile

import android.graphics.Color
import android.graphics.LinearGradient
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.keakr.R
import android.graphics.Shader
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.shapes.RectShape
import android.graphics.drawable.ShapeDrawable
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.keakr.data.model.Response
import com.example.keakr.data.model.User
import kotlinx.android.synthetic.main.profile_fragment.*




class ProfileFragment : Fragment() {

    private lateinit var v : View
    private lateinit var scrollListener: RecyclerView.OnScrollListener
    private lateinit var beatAdapter: BeatAdapter
    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.profile_fragment, container, false)
        return v;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        changeBackgroundGradiant("#FFFFFF")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        viewModel.get_profile(15)
        viewModel.get_keaks(15)
        viewModel.get_response().observeForever(object : Observer<Response> {
            override fun onChanged(someData: Response?) {
                // do something with someData
                if (someData == null)
                    return
                if (someData.user != null){
                    setProfile(someData.user)
                    changeBackgroundGradiant(someData.user.dominantColor!!)
                } else {
                    beatAdapter.addItems(someData.items.subList(0, 3))
                }
            }
        })
    }

    private fun initViews(){
        val llm = LinearLayoutManager(context)
        list.layoutManager = llm
        beatAdapter = BeatAdapter(context!!)
        list.adapter  = beatAdapter
        setRecyclerViewScrollListener()
    }

    private fun setProfile(user: User){
        username.text = user.username
        bio.text = user.biography
        Glide.with(context!!)
            .load(user.pictureUrl)
            .into(picture)
    }

    private fun changeBackgroundGradiant(dominantColor : String){
        val drawable = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(Color.parseColor(dominantColor), resources.getColor(android.R.color.transparent))
        )
        v.background = drawable
    }

    private fun setRecyclerViewScrollListener() {
        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager?.itemCount
                val lastVisibleItemPosition: LinearLayoutManager? = recyclerView.layoutManager as LinearLayoutManager?
                if (totalItemCount!! == lastVisibleItemPosition?.findLastVisibleItemPosition()?.plus(1)!!) {
                    loadData(totalItemCount!!)
                    var maxCount = 15
                    if (maxCount != 0 && maxCount + 1 <= totalItemCount){
                        recyclerView.removeOnScrollListener(scrollListener)
                    }
                }
            }
        }
        list.addOnScrollListener(scrollListener)
    }

    private fun loadData(page : Int){
        if (page == -1)
            return
        //TODO: DO the query
    }

}
