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
import android.graphics.drawable.shapes.RectShape
import android.graphics.drawable.ShapeDrawable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun initViews(){
        val llm = LinearLayoutManager(context)
        list.layoutManager = llm
        beatAdapter = BeatAdapter(context!!)
        list.adapter  = beatAdapter
        setRecyclerViewScrollListener()
    }

    private fun changeBackgroundGradiant(dominantColor : String){
        val h = v.height
        val mDrawable = ShapeDrawable(RectShape())
        mDrawable.paint.shader = LinearGradient(
            0f,
            0f,
            0f,
            h.toFloat(),
            Color.parseColor(dominantColor),
            Color.parseColor("#131314"),
            Shader.TileMode.REPEAT
        )
        v.background = mDrawable
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