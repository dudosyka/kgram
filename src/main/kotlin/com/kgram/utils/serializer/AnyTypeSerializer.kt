package com.kgram.utils.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

class AnyTypeSerializer(
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("data", PrimitiveKind.STRING)
) : KSerializer<Any?> {

    private fun anyToJsonElement(value: Any?): JsonElement {
        return when (value) {
            null -> JsonNull
            is JsonElement -> value
            is Boolean -> JsonPrimitive(value)
            is Number -> JsonPrimitive(value)
            is String -> JsonPrimitive(value)
            is Iterable<*> -> JsonArray(value.map { anyToJsonElement(it) })
            is Map<*, *> -> JsonObject(value.map { it.key.toString() to anyToJsonElement(it.value) }.toMap())
            else -> throw Exception("Not implemented type ${value::class}=${value}}")
        }
    }

    private fun jsonPrimitiveToAny(value: JsonPrimitive): Any? {
        val content = value.content
        if (value.isString){
            if (content.equals("null", ignoreCase = true)){
                return null
            }
            if (content.equals("true", ignoreCase = true)){
                return true
            }
            if (content.equals("false", ignoreCase = true)){
                return false
            }

            return content
        }

        val boolean = value.booleanOrNull

        if (boolean != null)
            return boolean

        val longValue = content.toLongOrNull()
        if (longValue!=null){
            return longValue
        }

        val floatValue = content.toFloatOrNull()
        if (floatValue!=null){
            return floatValue
        }
        throw Exception("Json serialize error for $content")
    }

    private fun jsonElementToAny(value: JsonElement): Any? {
        return when (value) {
            is JsonNull -> null
            is JsonPrimitive -> jsonPrimitiveToAny(value)
            is JsonObject -> value.map { it.key to jsonElementToAny(it.value) }.toMap()
            is JsonArray -> value.map { jsonElementToAny(it) }
        }
    }

    override fun deserialize(decoder: Decoder): Any? {
        val jsonElement = decoder.decodeSerializableValue(JsonElement.serializer())
        return jsonElementToAny(jsonElement)
    }

    override fun serialize(encoder: Encoder, value: Any?) {
        encoder.encodeSerializableValue(JsonElement.serializer(), this.anyToJsonElement(value))
    }
}