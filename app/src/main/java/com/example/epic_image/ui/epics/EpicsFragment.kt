package com.example.epic_image.ui.epics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.epic_image.R
import com.example.epic_image.repository.model.EpicResponseItem
import com.example.epic_image.repository.model.EpicsResponse
import com.example.epic_image.repository.network.EpicService
import com.example.epic_image.utils.CustomViewModelFactory
import kotlinx.android.synthetic.main.fragment_epics.*
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class EpicsFragment() : Fragment() {

    private val dateSelect by lazy { arguments?.getString(date) }
    companion object {
        const val TAG = "EpicsFragment"

        private const val date = "";

        fun newInstance(dateSelect: String): Fragment {
            val args = Bundle()
            args.putString(date,dateSelect)
            val fragment = EpicsFragment()
            fragment.arguments = args
            return fragment
        }

    }

    private var epicList: List<EpicResponseItem>? = null
    private var mAdapter: EpicsAdapter? = null

    private val mViewmodel: EpicsFragmentViewModel by lazy {
        val factory = CustomViewModelFactory(activity!!.application)
        ViewModelProvider(this, factory).get(EpicsFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_epics, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        dateSelect?.let { getAllEpic(it) }
    }

    private fun init() {
        recyclerViewEpicsList.layoutManager = LinearLayoutManager(activity)
        recyclerViewEpicsList.isNestedScrollingEnabled = false
        recyclerViewEpicsList.setHasFixedSize(false)

    }

    private fun getAllEpic(date: String) {

        mViewmodel.getEpics(date, object : EpicService.CallbackResponse<EpicsResponse> {
            override fun onResponse(response: EpicsResponse) {
                val epicsList = response
                mAdapter =
                    EpicsAdapter(activity!!.applicationContext, this@EpicsFragment, epicsList)
                recyclerViewEpicsList.adapter = mAdapter
            }

            override fun onFailure(t: Throwable, res: Response<*>?) {
            }

        })


    }

    fun updateList() {
        mAdapter!!.notifyDataSetChanged()
    }

}