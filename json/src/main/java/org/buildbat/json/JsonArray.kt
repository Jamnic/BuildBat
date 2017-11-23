package org.buildbat.json

import org.json.simple.JSONArray
import org.json.simple.JSONObject

// TODO bad design - this class does too much
// TODO bad design - should array 'key' be passed here?
class JsonArray(
        private val key: String,
        private val jsonList: List<JsonObject>
) {

    constructor(key: String, jsonArray: JSONArray) : this(key, jsonArray.map { json -> JsonObject(json as JSONObject) })

    fun list(): List<JsonObject> {
        return jsonList
    }

    fun find(keyValue: String?): JsonObject? {
        return jsonList.find { it[key] == keyValue ?: "" }
    }

    fun save(jsonObject: JsonObject): JsonArray {
        return JsonArray(key, remove(jsonObject).list().plus(jsonObject))
    }

    fun remove(jsonObject: JsonObject): JsonArray {
        return remove(jsonObject[key])
    }

    fun remove(keyValue: String): JsonArray {
        return JsonArray(key, jsonList.minus(find(keyValue) ?: JsonObject()))
    }

    fun json(): JSONArray {
        val jsonArray = JSONArray()
        jsonArray.addAll(jsonList.map { it.json() })
        return jsonArray
    }
}