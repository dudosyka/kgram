package com.kgram.types.methods.getupdates

import com.kgram.types.methods.MethodResponse
import com.kgram.utils.serializer.AnyTypeSerializer
import kotlinx.serialization.Serializable

@Serializable
data class GetUpdatesMethodResponse(
    val ok: Boolean,
    val result: List<Map<String, @Serializable(with = AnyTypeSerializer::class) Any?>>
): MethodResponse()