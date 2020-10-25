package com.example.epic_image.ui.main

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
import kotlinx.android.synthetic.main.item_list.view.*
import javax.security.auth.callback.Callback

class MainAdapter(
    private val context: Context,
    private val callbackItemClick: CallbackItemClick,
    private val epicList: List<EpicResponseItem>?

) : RecyclerView.Adapter<MainAdapter.MainHolder>() {

    class MainHolder(v: View) : RecyclerView.ViewHolder(v) {
        internal var view = v
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return MainHolder(view)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        epicList?.get(position).let { epic ->

            val dateUrl = epic?.date?.replace("-", "/")
            val imageUrl = "https://api.nasa.gov/EPIC/archive/natural/${dateUrl}/png/${epic?.image}.png?api_key=${ApiKey.API_KEY}"

            Glide.with(context)
                .load(imageUrl)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.ic_launcher_background)
                )
                .into(holder.view.imageCardView)

            holder.view.cardView.setOnClickListener {
                callbackItemClick.onItemClick(epic!!)
            }
        }
    }

    override fun getItemCount(): Int {
        return epicList!!.size
    }
}