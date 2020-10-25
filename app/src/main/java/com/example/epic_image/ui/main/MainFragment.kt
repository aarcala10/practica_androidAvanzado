package com.example.epic_image.ui.main

import android.os.Bundle
import android.telecom.Call
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.epic_image.R
import com.example.epic_image.repository.model.EpicResponseItem
import com.example.epic_image.utils.CustomViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : Fragment(), CallbackItemClick {

    companion object {
        const val TAG = "MainFragment"
        fun newInstance() = MainFragment()
    }

    private var mAdapter: MainAdapter? = null

    private val mViewmodel: MainFragmentViewModel by lazy {
        val factory = CustomViewModelFactory(activity!!.application)
        ViewModelProvider(this, factory).get(MainFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getLocalAllEpic()

    }

    private fun init() {
        recyclerViewMainList.layoutManager = LinearLayoutManager(activity)
        recyclerViewMainList.isNestedScrollingEnabled = false
        recyclerViewMainList.setHasFixedSize(false)
    }

    private fun getLocalAllEpic() {
        mViewmodel.getLocalAllEpic().observe(viewLifecycleOwner, Observer { epicList ->
            mAdapter = MainAdapter(activity!!.applicationContext, this, epicList)
            recyclerViewMainList.adapter = mAdapter
        })
    }

    override fun onItemClick(epicResponseItem: EpicResponseItem) {

    }
}