package com.example.epic_image.ui.dates

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.epic_image.R
import com.example.epic_image.repository.model.DatesResponse
import com.example.epic_image.repository.model.DatesResponseItem
import com.example.epic_image.repository.network.EpicService
import com.example.epic_image.ui.epics.EpicsActivity
import com.example.epic_image.ui.epics.EpicsActivity.Companion.OBJECT_DATE
import com.example.epic_image.ui.epics.EpicsActivity.Companion.REQUEST_CODE
import com.example.epic_image.utils.CustomViewModelFactory
import kotlinx.android.synthetic.main.fragment_dates.*
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class DatesFragment : Fragment(), CallbackDateItemClick {

    companion object {
        const val TAG = "DatesFragment"
        fun newInstance() = DatesFragment()

    }

    private var dateList: List<DatesResponseItem>? = null
    private var mAdapter: DatesAdapter? = null

    private val mViewmodel: DatesFragmentViewModel by lazy {
        val factory = CustomViewModelFactory(activity!!.application)
        ViewModelProvider(this, factory).get(DatesFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dates, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getAllDates()
    }

    private fun init() {
        recyclerViewDateList.layoutManager = LinearLayoutManager(activity)
        recyclerViewDateList.isNestedScrollingEnabled = false
        recyclerViewDateList.setHasFixedSize(false)
    }

    private fun getAllDates() {
        mViewmodel.getDates(object : EpicService.CallbackResponse<DatesResponse> {
            override fun onResponse(response: DatesResponse) {
                dateList = response
                mAdapter = DatesAdapter(activity!!.applicationContext, this@DatesFragment, dateList)
                recyclerViewDateList.adapter = mAdapter
            }

            override fun onFailure(t: Throwable, res: Response<*>?) {
                mAdapter = DatesAdapter(activity!!.applicationContext, this@DatesFragment, dateList)
                recyclerViewDateList.adapter = mAdapter


            }
        })
    }


    override fun onItemClick(dateItemClick: DatesResponseItem) {
        val date = dateItemClick.date?.replace("-","/")
        activity?.let { fragment ->
            Intent(fragment, EpicsActivity::class.java).apply {

                arguments = Bundle().apply {
                    putSerializable(OBJECT_DATE, dateItemClick)
                }
                arguments?.let { args ->
                    putExtras(args)
                }

                putExtra("EXTRA_DATE", date)
                fragment.startActivityForResult(this, REQUEST_CODE)
            }
        }

    }

    fun updateList() {
        mAdapter!!.notifyDataSetChanged()
    }
}