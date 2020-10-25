package com.example.epic_image.ui.dates

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.epic_image.R
import com.example.epic_image.repository.model.DatesResponseItem
import kotlinx.android.synthetic.main.item_date_list.view.*

class DatesAdapter(
    private val context: Context,
    private val callbackDateItemClick: CallbackDateItemClick,
    private val dateList: List<DatesResponseItem>?
) : RecyclerView.Adapter<DatesAdapter.DateHolder>() {

    class DateHolder(v: View) : RecyclerView.ViewHolder(v) {
        internal var view = v
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_date_list, parent, false)
        return DateHolder(view)
    }

    override fun onBindViewHolder(holder: DateHolder, position: Int) {
        dateList?.get(position).let { date ->
            holder.view.cardView.dateText.text = date?.date

            holder.view.cardView.setOnClickListener {
                callbackDateItemClick.onItemClick(date!!)
            }
        }
    }


    override fun getItemCount(): Int {
        return dateList!!.size
    }
}