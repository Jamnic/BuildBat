package org.buildbat

import org.buildbat.filesystem.file.BaseFile
import org.buildbat.filesystem.file.WritableFile
import org.buildbat.json.JsonArray
import org.buildbat.json.JsonObject
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser

class JsonFile(
        private val key: String,
        private val file: BaseFile
) {

    fun jsonArray(): JsonArray {
        return JsonArray(key, (JSONParser().parse(file.read()) as JSONArray))
    }

    fun jsonObject(): JsonObject {
        return JsonObject((JSONParser().parse(file.read()) as JSONObject))
    }

    fun write(array: JsonArray) {
        WritableFile(file).write(array.json().toJSONString())
    }

    fun write(array: JsonObject) {
        WritableFile(file).write(array.json().toJSONString())
    }
}