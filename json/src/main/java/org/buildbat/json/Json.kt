package org.buildbat.json

interface Json {
    fun json(): JsonObject
    fun name(): String
}