package com.example.fanfun.utils

import android.app.Activity
import android.view.View
import androidx.annotation.IdRes
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

private fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)

typealias ErrorHandler = ((Int, String) -> Unit)
typealias SuccessHandler<T> = ((T) -> Unit)
typealias SimpleHandler = (() -> Unit)

const val ERROR_CODE_GENERIC = -1
const val FAILURE_CODE = -2
const val TRIST_ID_ERROR = 1
const val FEA_ERROR = 2
private const val MESSAGE_GENERIC = "Error desconocido"

fun <T> Call<T>.enqueue (
    onSuccess: SuccessHandler<T>? = null,
    onError: ErrorHandler? = null
){
    enqueue(object: Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful) {
                onSuccess?.let { handler ->
                    response.body()?.let{body ->
                        handler(body)
                    } ?: onError?.let { error ->
                        error(ERROR_CODE_GENERIC, MESSAGE_GENERIC)
                    }
                }
            } else {
                onError?.let { errorHandler ->
                    response.errorBody()?.let {
                        errorHandler (ERROR_CODE_GENERIC, MESSAGE_GENERIC)
                    } ?: errorHandler (response.code(), response.message())
                }
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            onError?.let {
                t.printStackTrace()
                it(FAILURE_CODE, t.message.toString())
            }
        }

    })
}