package com.example.fanfun.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.fanfun.R
import com.example.fanfun.model.Request
import com.example.fanfun.ui.pending.PendingFragment
import com.example.fanfun.utils.bind
import com.example.fanfun.utils.fullName
import com.example.fanfun.utils.loadImage
import com.google.android.material.button.MaterialButton

class PendingAdapter(private val mFragment: PendingFragment, var pendingList: ArrayList<Request>): RecyclerView.Adapter<PendingAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.cardview_pending, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return pendingList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reason: TextView by holder.itemView.bind(R.id.pending_client_reason)
        val name: TextView by holder.itemView.bind(R.id.pending_client_name)
        val time: TextView by holder.itemView.bind(R.id.pending_client_time)
        val image: AppCompatImageView by holder.itemView.bind(R.id.pending_client_picture)
        val comment: MaterialButton by holder.itemView.bind(R.id.pending_comment_button)
        val record: MaterialButton by holder.itemView.bind(R.id.pending_card_action_button)

        name.text = fullName(pendingList[position].user.name, pendingList[position].user.lastname)
        reason.text = pendingList[position].reason

        loadImage(mFragment.context!!,pendingList[position].user.picture,image)

        comment.setOnClickListener { mFragment.showDialog(pendingList[position].message!!, pendingList[position].reason!!) }
        record.setOnClickListener { mFragment.toRecord(pendingList[position]) }
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}