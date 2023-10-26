package com.kgram.core

import com.kgram.core.api.ApiDispatcher
import com.kgram.types.methods.Method
import com.kgram.types.methods.close.CloseMethod
import com.kgram.utils.kodein.KodeinService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.actor
import org.kodein.di.DI
import org.kodein.di.instance

class TelegramSender(di: DI) : KodeinService(di) {
    private val dispatcher: ApiDispatcher by instance()

    @OptIn(ObsoleteCoroutinesApi::class)
    private val onSendChannel = CoroutineScope(Job()).actor<Method<*>> {
        for (apiCall in this) {
            when (apiCall) {
                is CloseMethod -> dispatcher.call(apiCall)
            }
        }
    }
}