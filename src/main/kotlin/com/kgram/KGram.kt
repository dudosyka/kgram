package com.kgram

import com.kgram.core.TelegramSender
import com.kgram.core.TelegramUpdatesListener
import com.kgram.core.UpdateProcessor
import com.kgram.core.api.ApiDispatcher
import com.kgram.utils.kodein.bindSingleton
import org.kodein.di.DI
import org.kodein.di.instance

class KGram {
    private val kodein = DI {
        bindSingleton { ApiDispatcher(it) }
        bindSingleton { TelegramSender(it) }
        bindSingleton { TelegramUpdatesListener(it) }
        bindSingleton { UpdateProcessor(it) }
    }

    val apiDispatcher: ApiDispatcher by kodein.di.instance<ApiDispatcher>()
    val telegramSender: TelegramSender by kodein.di.instance<TelegramSender>()
    val telegramUpdatesListener: TelegramUpdatesListener by kodein.di.instance<TelegramUpdatesListener>()
    val updateProcessor: UpdateProcessor by kodein.di.instance<UpdateProcessor>()

    init {

    }
}