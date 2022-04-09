package com.startup.decofflaundry.ui

import android.view.LayoutInflater
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.util.Log
import com.google.android.exoplayer2.util.MimeTypes
import com.startup.decofflaundry.R
import com.startup.decofflaundry.base.BaseViewBindingActivity
import com.startup.decofflaundry.databinding.ActivityExoplayerBinding
private const val TAG = "PlayerActivity"
class ExoplayerActivity : BaseViewBindingActivity<ActivityExoplayerBinding>() {
    private var player: ExoPlayer? = null
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition = 0L
    private val playbackStateListener: Player.Listener = playbackStateListener()
    override fun onInit() {
        return
    }

    override fun getActivityBinding(layoutinflater: LayoutInflater): ActivityExoplayerBinding {
        return ActivityExoplayerBinding.inflate(layoutInflater)
    }

    //create exoplayer builder
    private fun initiaLizePlayer() {

        //untuk ketika bandwith pengguna jelek kualitas akan di kecilkan
        val trackSelector = DefaultTrackSelector(this).apply {
            setParameters(buildUponParameters().setMaxVideoSizeSd())
        }
        player = ExoPlayer.Builder(this)
            .setTrackSelector(trackSelector)
            .build()
            .also { exoPlayer ->
                bind?.exoplayerMedia?.player = exoPlayer
//                val mediaItem = MediaItem.fromUri(getString(R.string.media_url_mp3))
//                exoPlayer.setMediaItem(mediaItem)
//                val secondMediaItem = MediaItem.fromUri(getString(R.string.media_url_mp3))
//                exoPlayer.addMediaItem(secondMediaItem);

                val mediaItem = MediaItem.Builder()
                    .setUri(getString(R.string.media_url_dash))
                    .setMimeType(MimeTypes.APPLICATION_MPD)
                    .build()
                exoPlayer.addMediaItem(mediaItem)
                exoPlayer.playWhenReady = playWhenReady
                exoPlayer.seekTo(currentWindow, playbackPosition)
                exoPlayer.addListener(playbackStateListener)
                exoPlayer.prepare()
            }

    }

    private fun releasePLayer() {
        player?.run {
            playbackPosition = this.currentPosition
            currentWindow = this.currentWindowIndex
            playWhenReady = this.playWhenReady
            removeListener(playbackStateListener)
            release()
        }
        player = null
    }

    private fun playbackStateListener() = object : Player.Listener {
        override fun onPlaybackStateChanged(playbackState: Int) {
            val stateString: String = when (playbackState) {
                ExoPlayer.STATE_IDLE -> "ExoPlayer.STATE_IDLE      -"
                ExoPlayer.STATE_BUFFERING -> "ExoPlayer.STATE_BUFFERING -"
                ExoPlayer.STATE_READY -> "ExoPlayer.STATE_READY     -"
                ExoPlayer.STATE_ENDED -> "ExoPlayer.STATE_ENDED     -"
                else -> "UNKNOWN_STATE             -"
            }
            Log.d(TAG, "changed state to $stateString")
        }
    }

    override fun onStart() {
        super.onStart()
        initiaLizePlayer()
    }

    override fun onStop() {
        super.onStop()

        releasePLayer()
    }
}
