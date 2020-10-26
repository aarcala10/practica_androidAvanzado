package com.example.epic_image.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.epic_image.R
import com.example.epic_image.repository.model.DatesResponseItem
import com.example.epic_image.ui.dates.CallbackDateItemClick
import kotlinx.android.synthetic.main.item_date_list.view.*

class MainAdapter(
    private val context: Context,
    private val callbackDateItemClick: CallbackDateItemClick,
    private val dateList: List<DatesResponseItem>?

) : RecyclerView.Adapter<MainAdapter.MainHolder>() {

    class MainHolder(v: View) : RecyclerView.ViewHolder(v) {
        internal var view = v
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_date_list, parent, false)
        return MainHolder(view)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        dateList?.get(position).let { date ->
            holder.view.cardView.dateText.text = date?.date

            holder.view.cardView.setOnClickListener {
                callbackDateItemClick.onItemClick(date!!)
            }
        }
    }

    override fun getItemCount(): Int {
        if (dateList == null) {
            return 0
        } else return dateList.size
    }
}