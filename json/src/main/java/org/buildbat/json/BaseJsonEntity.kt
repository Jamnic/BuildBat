package org.buildbat.json

class BaseJsonEntity(
        private val key: String,
        private val jsonObject: JsonObject = JsonObject()
) : JsonEntity {

    override fun json(): JsonObject {
        return jsonObject
    }

    override fun key(): String {
        return key
    }
}