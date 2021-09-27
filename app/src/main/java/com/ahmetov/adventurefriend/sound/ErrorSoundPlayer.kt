package com.ahmetov.adventurefriend.sound

import android.content.Context
import android.media.MediaPlayer
import com.ahmetov.adventurefriend.R

object ErrorSoundPlayer {
    fun play(context: Context) {
        MediaPlayer.create(context, R.raw.monster)
            .start()
    }
}