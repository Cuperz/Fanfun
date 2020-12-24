package com.example.fanfun.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.fanfun.R
import com.example.fanfun.model.Request
import com.example.fanfun.ui.sketch.SketchFragment
import com.example.fanfun.utils.User
import com.example.fanfun.utils.bind
import com.example.fanfun.utils.loadImage
import com.google.android.material.button.MaterialButton

class SketchAdapter(private val mFragment: SketchFragment, var sketchList: ArrayList<User>): RecyclerView.Adapter<SketchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.cardview_sketch, parent, false)
        return SketchAdapter.ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reason: TextView by holder.itemView.bind(R.id.sketch_client_reason)
        val name: TextView by holder.itemView.bind(R.id.sketch_client_name)
        val time: TextView by holder.itemView.bind(R.id.sketch_client_time)
        val image: AppCompatImageView by holder.itemView.bind(R.id.sketch_client_picture)
        val comment: MaterialButton by holder.itemView.bind(R.id.sketch_comment_button)
        val playButton: MaterialButton by holder.itemView.bind(R.id.sketch_card_action_button)
        val videoAmount: TextView by holder.itemView.bind(R.id.video_amount)
        val addVideo: MaterialButton by holder.itemView.bind(R.id.sketch_add_button)

        loadImage(mFragment.context!!,sketchList[position].userPicture,image)
        videoAmount.text = sketchList[position].userVideos?.size.toString()
        reason.text = sketchList[position].userReason
        name.text = sketchList[position].userName

        comment.setOnClickListener { mFragment.showDialog(sketchList[position].userMessage!!, sketchList[position].userReason!!) }
        playButton.setOnClickListener {
            val request = Request(sketchList[position].requestId!!, name = sketchList[position].userName)
            mFragment.toVideoList(request)
        }
        addVideo.setOnClickListener {
            val request = Request(sketchList[position].requestId!!,
                    name = sketchList[position].userName,
                    message = sketchList[position].userMessage!!,
                    reason = sketchList[position].userReason!!,
                    picture = sketchList[position].userPicture)
            mFragment.newVideo(request)
        }
    }

    override fun getItemCount(): Int {
        return sketchList.size
    }

    fun updateList(updatedList: ArrayList<User>){
        if (updatedList.isEmpty()){
            sketchList.clear()
        }else {
            sketchList.clear()
            sketchList.addAll(updatedList)
            notifyDataSetChanged()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}