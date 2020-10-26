package com.example.epic_image.ui.epics

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.epic_image.R
import com.example.epic_image.repository.model.EpicResponseItem
import com.example.epic_image.utils.ApiKey
import kotlinx.android.synthetic.main.item_epics_list.view.*

class EpicsAdapter(
    private val context: Context,
    epicsList1: EpicsFragment,
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
            val year = epic?.date?.substring(0, 4)
            val month = epic?.date?.substring(5, 7)
            val day = epic?.date?.substring(8, 10)

            val image = epic?.image
            val url =
                "https://api.nasa.gov/EPIC/archive/natural/${year}/${month}/${day}/png/${image}.png?api_key=${ApiKey.API_KEY}"
            Glide.with(context)
                .load(url)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.ic_launcher_foreground)
                )
                .into(holder.itemView.imageEpicCardView)
        }

    }


    override fun getItemCount(): Int {
        return epicsList!!.size
    }
}