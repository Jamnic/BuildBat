package org.buildbat.log.configuration

import org.buildbat.json.JsonObject

class LogConfiguration(
        private val name: String,
        private val number: Long
) {

    constructor(
            name: String
    ) : this(name, 0L)

    constructor(
            json: JsonObject
    ) : this(json["name"], json["number"].toLong())

    fun nextId(): LogConfiguration {
        return LogConfiguration(name, number + 1)
    }

    fun id(): Long {
        return number
    }

    fun json(): JsonObject {
        return JsonObject()
                .add("name" to name,
                        "number" to number.toString())
    }
}