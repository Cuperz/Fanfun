package com.example.fanfun.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.fanfun.R
import com.example.fanfun.model.Request
import com.example.fanfun.ui.sent.SentFragment
import com.example.fanfun.utils.bind
import com.google.android.material.button.MaterialButton

class SentAdapter(var mFragment: SentFragment, var sentList:ArrayList<Request>): RecyclerView.Adapter<SentAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.cardview_sent, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reason: TextView by holder.itemView.bind(R.id.pending_client_reason)
        val name: TextView by holder.itemView.bind(R.id.pending_client_name)
        val time: TextView by holder.itemView.bind(R.id.pending_client_time)
        val image: AppCompatImageView by holder.itemView.bind(R.id.sent_client_picture)
        val actionButton: MaterialButton by holder.itemView.bind(R.id.sent_card_action_button)

        //loadImage(mFragment.context!!,sentList[position].id,image)
        //reason.text = sentList[position].reason

        actionButton.setOnClickListener { mFragment.playVideo(sentList[position].url) }
    }

    override fun getItemCount(): Int {
        return sentList.size
    }
}