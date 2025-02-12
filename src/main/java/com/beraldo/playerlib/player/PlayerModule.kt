/*
 * Copyright 2018 Filippo Beraldo. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.beraldo.playerlib.player

import android.content.Context
import com.beraldo.playerlib.PlayerService
import com.beraldo.playerlib.R
import com.beraldo.playerlib.media.DescriptionAdapter
import com.google.android.exoplayer2.ui.PlayerNotificationManager

/**
 * Created by Filippo Beraldo on 29/11/2018.
 * http://github.com/beraldofilippo
 *
 * Just a simple injection object, builds stuff.
 */
object PlayerModule {
    fun getPlayerHolder(context: Context, streamUrl: String, streamPosition: Long) =
        PlayerHolder(context, streamUrl, streamPosition, PlayerState())

    fun getPlayerNotificationManager(context: Context,
                                     listener: PlayerNotificationManager.NotificationListener): PlayerNotificationManager =
        PlayerNotificationManager.Builder(
            context,
            PlayerService.NOTIFICATION_ID,
            PlayerService.NOTIFICATION_CHANNEL
        ).apply {
            setChannelNameResourceId(R.string.app_name)
            setMediaDescriptionAdapter(getDescriptionAdapter(context))
            setNotificationListener(listener)
        }.build().apply {
            //setOngoing(true)
            setUsePreviousAction(false)
            setUseNextAction(false)
            setUseStopAction(true)
        }

    private fun getDescriptionAdapter(context: Context) = DescriptionAdapter(context)
}