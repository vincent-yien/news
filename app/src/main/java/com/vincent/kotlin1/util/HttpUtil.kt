package com.vincent.kotlin1.util

import com.vincent.kotlin1.synccallback.HttpCallBack
import okhttp3.*
import java.io.IOException

class HttpUtil {

    companion object {
        fun sendGet(callback: HttpCallBack, url: String) {
            var client = OkHttpClient()
            var request = Request.Builder().url(url).get().build()
            var call = client.newCall(request)
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    callback.onFial(e.toString())
                }

                override fun onResponse(call: Call, response: Response) {
                    response.body()?.string()?.let { callback.onSuccess(it) }
                }
            })
        }

        fun senPost(callback: HttpCallBack,url : String, requestBody: RequestBody){
            var client = OkHttpClient()
            var request = Request.Builder().url(url).post(requestBody).build()
            var call = client.newCall(request)
            call.enqueue( object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                    callback.onFial(e.toString())
                }

                override fun onResponse(call: Call, response: Response) {
                    callback.onSuccess(response.body().toString())
                    response.body()?.string()?.let { callback.onSuccess(it) }
                }
            })
        }
    }

}