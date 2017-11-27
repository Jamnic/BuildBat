package org.buildbat.json

open class EmptyJsonEntity : JsonEntity {
    override fun json() = throw IllegalStateException("Cannot convert empty object to json!")
    override fun key() = throw IllegalStateException("Cannot get name from empty object!")
}