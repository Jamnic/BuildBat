package org.buildbat.json

import org.json.simple.JSONObject

class JsonObject(
        // TODO bad design - consider making Pair<String, String> as base
        private val base: JSONObject
) {

    constructor(
            vararg pairs: Pair<String, String>
    ) : this(JSONObject(mapOf<String, String>(*pairs)))

    operator fun get(attribute: String): String {
        return base[attribute] as String? ?: ""
    }

    // TODO bad design - no immutability
    fun add(vararg pairs: Pair<String, String>): JsonObject {
        base.putAll(mapOf(*pairs))
        return this
    }

    // TODO bad design - construct new JSONObject
    fun json(): JSONObject {
        return base
    }

    // TODO bad design
    fun params(): Map<String, String> {
        return base.toMap() as Map<String, String>
    }
}