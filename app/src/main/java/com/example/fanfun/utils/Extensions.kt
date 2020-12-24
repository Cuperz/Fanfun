package com.example.fanfun.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.view.View
import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.fanfun.R
import com.example.fanfun.model.Request
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T : View> Activity.bind(@IdRes res: Int): Lazy<T> {
    @Suppress("UNCHECKED_CAST")
    return unsafeLazy { findViewById<T>(res) }
}

fun <T : View> View.bind(@IdRes idRes: Int): Lazy<T> {
    @Suppress("UNCHECKED_CAST")
    return unsafeLazy { findViewById<T>(idRes) }
}

val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
const val REQUEST_PERMISSIONS_CODE = 10

private fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)

const val FROM_CAMERA = 1
const val FROM_SKETCH = 2
const val FROM_SENT = 3

fun loadImage(context: Context, image: Any?, imageView: ImageView){
        Glide.with(context)
            .load(image)
            .placeholder(R.drawable.ic_default_placeholder)
            .error(R.drawable.ic_default_placeholder)
            .transform(CircleCrop())
            .into(imageView)
}

fun checkPermission(activity: Activity, permision: String, doThen:()-> Unit){
    if (ContextCompat.checkSelfPermission(activity, permision) == PackageManager.PERMISSION_GRANTED){
        doThen()
    }else{
        ActivityCompat.requestPermissions(activity, arrayOf(permision), REQUEST_PERMISSIONS_CODE)
    }
}

fun checkAllPermissions(activity: Activity, doThen:()-> Unit){
    if (allPermissionsGranted(activity)){
        doThen()
    }else{
        ActivityCompat.requestPermissions(activity, REQUIRED_PERMISSIONS, REQUEST_PERMISSIONS_CODE)
    }
}

fun allPermissionsGranted(context: Context) = REQUIRED_PERMISSIONS.all {
    ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
}

fun String.toRequest(): Request{
    return  Gson().fromJson(this, Request::class.java)
}

fun Request.toJson(): String{
    return Gson().toJson(this)
}




fun Activity.forwardTransition(){
    this.overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
}

fun Activity.backwardTransition(){
    this.overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right)
}