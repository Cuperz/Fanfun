package com.example.fanfun.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fanfun.R
import com.example.fanfun.model.Model
import com.example.fanfun.ui.sketch.SketchFragment
import com.example.fanfun.utils.bind

class SketchAdapter(val fragment: SketchFragment, var sketchList: ArrayList<Model.Sketch>): RecyclerView.Adapter<SketchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.sketch_cardview, parent, false)
        return SketchAdapter.ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reason: TextView by holder.itemView.bind(R.id.pending_client_reason)
        val name: TextView by holder.itemView.bind(R.id.pending_client_name)
        val time: TextView by holder.itemView.bind(R.id.pending_client_time)
    }

    override fun getItemCount(): Int {
        return sketchList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}