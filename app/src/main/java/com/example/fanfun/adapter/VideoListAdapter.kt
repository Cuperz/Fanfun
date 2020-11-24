package com.example.fanfun.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fanfun.R
import com.example.fanfun.ui.videolist.VideoListActivity
import com.example.fanfun.utils.bind
import com.google.android.material.button.MaterialButton
import java.util.ArrayList

class VideoListAdapter(private val mActivity: VideoListActivity, var mVideoList: ArrayList<String>): RecyclerView.Adapter<VideoListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoListAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.cardview_video_list, parent, false)
        return VideoListAdapter.ViewHolder(v)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reason: TextView by holder.itemView.bind(R.id.list_client_time)
        val playButton: MaterialButton by holder.itemView.bind(R.id.play_video_list)
        val deleteButton: MaterialButton by holder.itemView.bind(R.id.video_delete_button)

        playButton.setOnClickListener { mActivity.toVideo(mVideoList[position]) }
        deleteButton.setOnClickListener { mActivity.deleteVideo(mVideoList[position]) }
    }

    override fun getItemCount(): Int {
        return mVideoList.size
    }

    fun videoDeleted(userVideos: ArrayList<String>?) {
        mVideoList.clear()
        mVideoList.addAll(userVideos!!)
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}