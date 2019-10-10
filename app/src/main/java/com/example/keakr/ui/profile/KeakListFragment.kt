package com.example.keakr.ui.profile


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.keakr.R
import com.example.keakr.data.model.Beat
import com.example.keakr.data.model.Response
import kotlinx.android.synthetic.main.fragment_keak_list.*
import kotlinx.android.synthetic.main.profile_fragment.*
import kotlinx.android.synthetic.main.profile_fragment.list

/**
 * A simple [Fragment] subclass.
 */
class KeakListFragment : Fragment() {

    private lateinit var v : View
    private lateinit var scrollListener: RecyclerView.OnScrollListener
    private lateinit var beatAdapter: BeatAdapter
    private lateinit var viewModel: ProfileViewModel

    companion object {
        fun newInstance(beats : List<Beat>) : KeakListFragment{
            val fragment = KeakListFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList("beats", ArrayList(beats))
            fragment.arguments = (bundle)
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_keak_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initToolbar() {
        toolbar.title = ""
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back_grey)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayUseLogoEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        viewModel.get_keaks(15)
        viewModel.get_response().observeForever(object : Observer<Response> {
            override fun onChanged(someData: Response?) {
                // do something with someData
                if (someData == null)
                    return
                beatAdapter.addItems(someData.items)
            }
        })
    }

    private fun initViews(){
        initToolbar()
        val llm = LinearLayoutManager(context)
        list.layoutManager = llm
        beatAdapter = BeatAdapter(context!!, false)
        list.adapter  = beatAdapter
        setRecyclerViewScrollListener()
    }

    private fun setRecyclerViewScrollListener() {
        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager?.itemCount
                val lastVisibleItemPosition: LinearLayoutManager? = recyclerView.layoutManager as LinearLayoutManager?
                if (totalItemCount!! == lastVisibleItemPosition?.findLastVisibleItemPosition()?.plus(1)!!) {
                    loadData(totalItemCount)
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
        viewModel.get_keaks(page * 15)
    }




}
