package com.kgram.core

import com.kgram.types.update.MessageUpdate
import com.kgram.types.update.Update
import com.kgram.utils.kodein.KodeinService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.actor
import org.kodein.di.DI

class UpdateProcessor(di: DI) : KodeinService(di) {
    @OptIn(ObsoleteCoroutinesApi::class)
    val updatesChannel = CoroutineScope(Job()).actor<Update>(capacity = Channel.BUFFERED) {
        for (update in this) {
            when (update) {
                is MessageUpdate -> true
                // ....
            }
        }
    }
}