package com.kgram.types.methods

import kotlinx.coroutines.CompletableDeferred
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
abstract class Method<T: MethodResponse>(@Transient var name: String = "") {
    @Transient var response: CompletableDeferred<T> = CompletableDeferred()
}