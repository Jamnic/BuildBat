package org.buildbat.json

interface JsonEntity {
    fun json(): JsonObject
    fun key(): String
}