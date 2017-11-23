package org.buildbat.execution.executable

import org.buildbat.execution.command.shell.ShellCommand
import org.buildbat.execution.process.Process
import org.buildbat.execution.process.ProcessBase

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