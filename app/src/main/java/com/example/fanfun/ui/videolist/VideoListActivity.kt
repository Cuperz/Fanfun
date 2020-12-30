package com.example.fanfun.ui.videolist

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fanfun.R
import com.example.fanfun.adapter.VideoListAdapter
import com.example.fanfun.model.Request
import com.example.fanfun.utils.*
import com.google.android.material.button.MaterialButton

class VideoListActivity: App(), VideoListContract.View {

    private lateinit var mRecycler: RecyclerView
    private lateinit var mAdapter: VideoListAdapter
    private val mProfilePicture: ImageView by bind(R.id.list_profile_button)
    private val mBackArrow: MaterialButton by bind(R.id.list_back_arrow)
    private val mRequestName: TextView by bind(R.id.username)
    private var mPresenter: VideoListContract.Presenter? = null
    private var mRequest: Request? =null
    private var mFrom: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_list)

        mRequest = intent.getStringExtra("request")?.toRequest()
        mFrom = intent.getIntExtra("from", FROM_SKETCH)
        mPresenter = VideoListPresenter(this)
        mRecycler = findViewById(R.id.video_list_recycler)
        mRecycler.layoutManager = LinearLayoutManager(this)
        initListener()
        loadImage(this,mPresenter?.getPhoto(), mProfilePicture)
        mRequestName.text = fullName(mRequest?.user!!.name, mRequest?.user!!.lastname)
        mBackArrow.setOnClickListener { onBackPressed() }
    }

    private fun initListener() {
        val userVideos: ArrayList<String> = mPresenter!!.getVideos(mRequest!!.id)
        mAdapter = VideoListAdapter(this, userVideos)
        mRecycler.adapter = mAdapter
        mAdapter.notifyDataSetChanged()
    }

    fun toVideo(path: String) {
        mPresenter?.toWatchVideo(path, mRequest!!)
    }

    fun deleteVideo(path: String, position: Int) {
        val deleteDialog = LayoutInflater.from(this).inflate(R.layout.dialog_delete,null)
        val dialogBuilder = AlertDialog.Builder(this).setView(deleteDialog)
        val dialogInstance = dialogBuilder.show()
        dialogInstance.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val cancelButton: MaterialButton = deleteDialog.findViewById(R.id.delete_dialog_cancel_button)
        cancelButton.setOnClickListener { dialogInstance.dismiss() }

        val deleteButton: MaterialButton = deleteDialog.findViewById(R.id.delete_dialog_confirm_button)
        deleteButton.setOnClickListener {
            mPresenter?.deleteVideo(mRequest!!.id,path, position)
            dialogInstance.dismiss()
        }
    }

    override fun videoDeleted(userVideos: ArrayList<String>?, position: Int) {
        mAdapter.videoDeleted(userVideos, position)
    }

    override fun userDeleted() {
        val saveDialog = LayoutInflater.from(this).inflate(R.layout.dialog_empty_list,null)
        val dialogBuilder = AlertDialog.Builder(this).setView(saveDialog)
        val dialogInstance = dialogBuilder.show()
        dialogInstance.setCancelable(false)
        dialogInstance.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val continueButton: MaterialButton = saveDialog.findViewById(R.id.empty_dialog_continue_button)
        continueButton.setOnClickListener {
            dialogInstance.dismiss()
            mPresenter?.toHome()
        }
    }

    override fun onBackPressed() {
        if (mFrom == FROM_RESULT){
            mPresenter?.toHome()
        }else {
            super.onBackPressed()
            backwardTransition()
        }
    }


}