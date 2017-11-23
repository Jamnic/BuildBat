package org.buildbat.json

class BaseJson(
        private val name: String
) : Json {

    override fun json(): JsonObject {
        return JsonObject()
    }

    override fun name(): String {
        return name
    }
}