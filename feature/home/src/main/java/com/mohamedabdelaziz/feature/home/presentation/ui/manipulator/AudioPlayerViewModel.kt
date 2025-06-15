package com.mohamedabdelaziz.feature.home.presentation.ui.manipulator

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AudioPlayerViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private var _exoPlayer: ExoPlayer? = null

    private val _currentlyPlayingUrl = MutableStateFlow<String?>(null)
    private val currentlyPlayingUrl: StateFlow<String?> = _currentlyPlayingUrl

    private val _isNowPlaying = MutableStateFlow(false)

    fun isPlaying(audioUrl: String): StateFlow<Boolean> {
        return combine(_isNowPlaying, _currentlyPlayingUrl) { playing, url ->
            playing && url == audioUrl
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)
    }
    fun initPlayer(audioUrl: String) {
        if (_exoPlayer == null) {
            _exoPlayer = ExoPlayer.Builder(context).build().apply {
                addListener(object : Player.Listener {
                    override fun onIsPlayingChanged(isPlayingNow: Boolean) {
                        _isNowPlaying.value = isPlayingNow
                    }
                })
            }
        }

        if (currentlyPlayingUrl.value != audioUrl) {
            _exoPlayer?.apply {
                stop()
                setMediaItem(MediaItem.fromUri(audioUrl))
                prepare()
            }
            _currentlyPlayingUrl.value = audioUrl
        }
    }

    fun togglePlayPause() {
        _exoPlayer?.let {
            if (it.isPlaying) it.pause() else it.play()
        }
    }

    override fun onCleared() {
        _exoPlayer?.release()
        _exoPlayer = null
    }

}
