package com.kgram.core.api

import com.kgram.types.methods.Method
import com.kgram.types.methods.MethodResponse
import com.kgram.utils.kodein.KodeinService
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.apache.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.kodein.di.DI

class ApiDispatcher(di: DI) : KodeinService(di) {
    val client = HttpClient(Apache) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
        defaultRequest {
            header("Content-Type", "application/json")
        }
    }

    suspend inline fun <reified T: MethodResponse, reified R: Method<T>> call(method: R) {
        val sendUrl = "https://api.telegram.org/bot/${method.name}"
        method.response.complete(client.post(sendUrl) {
            contentType(ContentType.Application.Json)
            setBody(method)
        }.body<T>())
    }
}