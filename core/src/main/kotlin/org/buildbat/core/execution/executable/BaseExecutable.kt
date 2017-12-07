package org.buildbat.core.execution.executable

import org.buildbat.core.execution.command.shell.ShellCommand
import org.buildbat.core.execution.process.Process
import org.buildbat.core.execution.process.ProcessBase

class BaseExecutable(
        private val command: ShellCommand
) : Executable {

    override fun execute(): Process {
        try {
            return ProcessBase(ProcessBuilder(command.command().split(" "))
                    .directory(command.executionDirectory().realFile())
                    .redirectErrorStream(true)
                    .start())
        } catch (e: Exception) {
            throw e
        }
    }
}