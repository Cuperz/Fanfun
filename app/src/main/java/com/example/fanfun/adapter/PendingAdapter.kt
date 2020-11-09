package com.example.fanfun.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fanfun.R
import com.example.fanfun.model.Model
import com.example.fanfun.ui.pending.PendingFragment
import com.example.fanfun.utils.bind
import com.google.android.material.button.MaterialButton

class PendingAdapter(val mFragment: PendingFragment, var pendingList: ArrayList<Model.Pending>): RecyclerView.Adapter<PendingAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.pending_cardview, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return pendingList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reason: TextView by holder.itemView.bind(R.id.pending_client_reason)
        val name: TextView by holder.itemView.bind(R.id.pending_client_name)
        val time: TextView by holder.itemView.bind(R.id.pending_client_time)
        val comment: MaterialButton by holder.itemView.bind(R.id.pending_comment_button)
        val record: MaterialButton by holder.itemView.bind(R.id.pending_card_action_button)

        comment.setOnClickListener { mFragment.showDialog() }
        record.setOnClickListener { mFragment.toRecord() }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}