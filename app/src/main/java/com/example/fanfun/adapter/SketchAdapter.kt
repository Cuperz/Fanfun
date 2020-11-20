package com.example.fanfun.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fanfun.R
import com.example.fanfun.model.Model
import com.example.fanfun.ui.sketch.SketchFragment
import com.example.fanfun.utils.User
import com.example.fanfun.utils.bind
import com.google.android.material.button.MaterialButton

class SketchAdapter(private val mFragment: SketchFragment, var sketchList: ArrayList<User>): RecyclerView.Adapter<SketchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.sketch_cardview, parent, false)
        return SketchAdapter.ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reason: TextView by holder.itemView.bind(R.id.sketch_client_reason)
        val name: TextView by holder.itemView.bind(R.id.sketch_client_name)
        val time: TextView by holder.itemView.bind(R.id.sketch_client_time)
        val comment: MaterialButton by holder.itemView.bind(R.id.sketch_comment_button)
        val playButton: MaterialButton by holder.itemView.bind(R.id.sketch_card_action_button)
        val videoAmount: TextView by holder.itemView.bind(R.id.video_amount)

        videoAmount.text = sketchList[0].userVideos?.size.toString()
        reason.text = sketchList[0].userReason
        name.text = sketchList[0].userName

        comment.setOnClickListener { mFragment.showDialog() }
        playButton.setOnClickListener { mFragment.toVideoList() }
    }

    override fun getItemCount(): Int {
        return sketchList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}