package org.buildbat.core.file

import org.buildbat.filesystem.file.BaseFile
import org.buildbat.filesystem.file.WritableFile
import org.buildbat.json.JsonArray
import org.buildbat.json.JsonObject
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser

interface JsonFile{
    fun jsonArray(): JsonArray
    fun jsonObject(): JsonObject
    fun write(array: JsonArray)
    fun write(array: JsonObject)
}

class BaseJsonFile(
        private val key: String,
        private val file: BaseFile
) : JsonFile {

    override fun jsonArray(): JsonArray {
        return JsonArray(key, (JSONParser().parse(file.read()) as JSONArray))
    }

    override fun jsonObject(): JsonObject {
        return JsonObject((JSONParser().parse(file.read()) as JSONObject))
    }

    override fun write(array: JsonArray) {
        WritableFile(file).write(array.json().toJSONString())
    }

    override fun write(array: JsonObject) {
        WritableFile(file).write(array.json().toJSONString())
    }
}