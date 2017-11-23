package org.buildbat.json

open class EmptyJson : Json {
    override fun json() = throw IllegalStateException("Cannot convert empty object to json!")
    override fun name() = throw IllegalStateException("Cannot get name from empty object!")
}