package org.buildbat.core.log.configuration

import org.buildbat.json.BaseJsonEntity
import org.buildbat.json.JsonEntity
import org.buildbat.json.JsonObject

class LogConfiguration(
        private val name: String,
        private val number: Long,
        private val jsonObject: JsonObject = JsonObject("name" to name, "number" to number.toString()),
        private val json: JsonEntity = BaseJsonEntity(name, jsonObject)
) : JsonEntity by json {

    constructor(
            name: String
    ) : this(name, 0L)

    constructor(
            jsonObject: JsonObject
    ) : this(
            jsonObject["name"],
            jsonObject["number"].toLong(),
            jsonObject)

    fun nextId(): LogConfiguration {
        return LogConfiguration(name, number + 1)
    }

    fun id(): Long {
        return number
    }
}