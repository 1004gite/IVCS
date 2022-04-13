package com.example.ivcs_android

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.ivcs_android.databinding.ActivityStreamingBinding
import com.example.ivcs_android.view.streaming.SetStreamingViews
import com.example.ivcs_android.view.streaming.Streaming
import com.example.ivcs_android.viewModel.StreamingBind

class StreamingActivity : AppCompatActivity() {

//    lateinit var mBinding : ActivityMainBinding
    lateinit var mBinding : ActivityStreamingBinding
    lateinit var setStreamingViews : SetStreamingViews
    lateinit var mStreaming: Streaming
    lateinit var streamingBind: StreamingBind

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityStreamingBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setTexttureView()
        initStreamingActivity()
    }

    fun setTexttureView(){
        mBinding.textureView.layoutParams.height = resources.displayMetrics.heightPixels/4
    }
    private fun initStreamingActivity(){
        setStreamingViews = SetStreamingViews(this, mBinding)
        setStreamingViews.setViews()

        mStreaming = Streaming(this,mBinding)
        mStreaming.initializePlayer()

        streamingBind = StreamingBind(mStreaming)
        streamingBind.initStreamingBind()
    }

    override fun onDestroy() {
        super.onDestroy()
        mStreaming.releasePlayer()
    }

}