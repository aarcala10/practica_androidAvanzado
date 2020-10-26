package com.example.epic_image.ui.main

import android.content.Intent
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
import com.example.epic_image.repository.model.DatesResponseItem
import com.example.epic_image.ui.dates.CallbackDateItemClick
import com.example.epic_image.ui.epics.EpicsActivity
import com.example.epic_image.utils.CustomViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : Fragment(), CallbackDateItemClick {

    companion object {
        const val TAG = "MainFragment"
        fun newInstance() = MainFragment()
    }

    private var dateList: List<DatesResponseItem>? = null
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
        getLocalAllDates()

    }

    private fun init() {
        recyclerViewMainList.layoutManager = LinearLayoutManager(activity)
        recyclerViewMainList.isNestedScrollingEnabled = false
        recyclerViewMainList.setHasFixedSize(false)
    }

    private fun getLocalAllDates() {
        mViewmodel.getLocalAllDate().observe(viewLifecycleOwner, Observer { dateList ->
            mAdapter = MainAdapter(activity!!.applicationContext, this, dateList)
            recyclerViewMainList.adapter = mAdapter
        })
    }


    override fun onItemClick(datesResponseItem: DatesResponseItem) {
        val date = datesResponseItem.date
        activity?.let { fragment ->
            Intent(fragment, EpicsActivity::class.java).apply {

                arguments = Bundle().apply {
                    putSerializable(EpicsActivity.OBJECT_DATE, datesResponseItem)
                }
                arguments?.let { args ->
                    putExtras(args)
                }

                putExtra("EXTRA_DATE", date)
                fragment.startActivityForResult(this, EpicsActivity.REQUEST_CODE)
            }
        }
    }
}