package com.kgram.core

import com.kgram.utils.kodein.KodeinService
import kotlinx.coroutines.*
import org.kodein.di.DI

class TelegramUpdatesListener(di: DI) : KodeinService(di) {
    suspend fun startPulling() {
        withContext(Dispatchers.IO) {
            while (true) {
                //CALL GetUpdates
                //Check for new updates
                //Push updates to updatesChannel
            }
        }
    }
}