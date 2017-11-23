package org.buildbat.execution.command.shell

import org.buildbat.json.JsonObject

class ShellCommandParam(
        private val command: String,
        private val jsonObject: JsonObject
) {

    companion object {
        private val PARAM_REGEX = Regex("\\{\\w+}")
    }

    fun resolve(): String {
        return command.replace(PARAM_REGEX, {
            val parameterValue = jsonObject[removeBrackets(it.value)]
            if (parameterValue == "")
                it.value
            else
                parameterValue
        })
    }

    private fun removeBrackets(value: String): String {
        return value
                .removePrefix("{")
                .removeSuffix("}")
    }
}