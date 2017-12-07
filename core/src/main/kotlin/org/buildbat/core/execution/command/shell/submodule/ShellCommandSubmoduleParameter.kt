package org.buildbat.core.execution.command.shell.submodule

import org.buildbat.core.plugin.project.EmptyProject
import org.buildbat.core.plugin.project.multimodule.MultiModuleProject
import org.buildbat.core.plugin.tomcat.project.BaseWarProject
import kotlin.text.Regex

class ShellCommandSubmoduleParameter(
        private val command: String,
        private val project: MultiModuleProject
) {

    companion object {
        private val PARAM_REGEX = Regex("\\[\\w+ \\{\\w+}]")
    }

    fun resolve(): String {
        return command.replace(PARAM_REGEX, {
            val submoduleContext = removeSquareBrackets(it.value)
            val submoduleName = submoduleContext.substringBefore(" ")
            val submoduleParam = submoduleContext.substringAfter(" ")
            val submodule = project.module(submoduleName)
            if (submodule is EmptyProject) {
                it.value
            } else {
                // TODO consider delegating it to ShellCommandParameter
                val parameterValue = BaseWarProject(submodule).json()[removeBrackets(submoduleParam)]
                if (parameterValue == "") {
                    it.value
                } else
                    parameterValue
            }
        })
    }

    private fun removeSquareBrackets(value: String): String {
        return value
                .removePrefix("[")
                .removeSuffix("]")
    }

    private fun removeBrackets(value: String): String {
        return value
                .removePrefix("{")
                .removeSuffix("}")
    }
}