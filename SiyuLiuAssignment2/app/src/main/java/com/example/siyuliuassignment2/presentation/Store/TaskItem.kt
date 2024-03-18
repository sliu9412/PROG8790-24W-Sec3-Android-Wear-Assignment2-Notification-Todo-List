package com.example.siyuliuassignment2.presentation.Store

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Serializable
data class TaskItem(
    val id: String,
    val taskName: String,
    @Serializable(with = LocalDateTimeSerializer::class)
    val due: LocalDateTime
)

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = LocalDateTime::class)
class LocalDateTimeSerializer : KSerializer<LocalDateTime> {
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        encoder.encodeString(value.format(formatter))
    }

    override fun deserialize(decoder: Decoder): LocalDateTime {
        return LocalDateTime.parse(decoder.decodeString(), formatter)
    }
}