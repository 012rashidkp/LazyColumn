package com.example.lazycolumn.Utils

import androidx.datastore.core.Serializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream


@Suppress("BlockingMethodInNonBlockingContext")
object TinyDBSerializer : Serializer<TinyDB> {
    override val defaultValue: TinyDB
        get() = TinyDB()

    override suspend fun readFrom(input: InputStream): TinyDB {
return try {
    Json.decodeFromString(
        deserializer = TinyDB.serializer(),
        string = input.readBytes().decodeToString()
    )
}catch (e:SerializationException){
    e.printStackTrace()
    defaultValue
}

    }

    override suspend fun writeTo(t: TinyDB, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = TinyDB.serializer(),
                value = t
            ).encodeToByteArray()
        )


    }
}