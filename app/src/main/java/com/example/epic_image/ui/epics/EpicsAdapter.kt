package com.example.epic_image.ui.epics

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.epic_image.R
import com.example.epic_image.repository.model.EpicResponseItem
import com.example.epic_image.ui.epics.CallbackEpicItemClick
import kotlinx.android.synthetic.main.item_date_list.view.*
import kotlinx.android.synthetic.main.item_date_list.view.cardView
import kotlinx.android.synthetic.main.item_list.view.*

class EpicsAdapter(
    private val context: Context,
    private val callbackEpicItemClick: CallbackEpicItemClick,
    private val epicsList: List<EpicResponseItem>?
) : RecyclerView.Adapter<EpicsAdapter.EpicHolder>() {

    class EpicHolder(v: View) : RecyclerView.ViewHolder(v) {
        internal var view = v
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpicHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_epics_list, parent, false)
        return EpicHolder(view)
    }

    override fun onBindViewHolder(holder: EpicHolder, position: Int) {
        epicsList?.get(position).let { epic ->
            holder.view.cardView.dateText.text = epic?.date

        }

    }


    override fun getItemCount(): Int {
        return epicsList!!.size
    }
}