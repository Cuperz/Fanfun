package com.example.fanfun.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fanfun.R
import com.example.fanfun.model.Model
import com.example.fanfun.ui.sent.SentFragment
import com.example.fanfun.utils.bind
import com.google.android.material.button.MaterialButton

class SentAdapter(var mFragment: SentFragment, var sentList:ArrayList<Model.Request>): RecyclerView.Adapter<SentAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.cardview_sent, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reason: TextView by holder.itemView.bind(R.id.pending_client_reason)
        val name: TextView by holder.itemView.bind(R.id.pending_client_name)
        val time: TextView by holder.itemView.bind(R.id.pending_client_time)
        val actionButton: MaterialButton by holder.itemView.bind(R.id.sent_card_action_button)

        actionButton.setOnClickListener { mFragment.playVideo() }
    }

    override fun getItemCount(): Int {
        return sentList.size
    }
}