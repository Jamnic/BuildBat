package org.buildbat.core.execution.executable

import org.buildbat.core.execution.command.shell.ShellCommand
import org.buildbat.core.execution.process.LoggedProcess
import org.buildbat.core.execution.process.Process
import org.buildbat.filesystem.file.File

class LoggedExecutable(
        private val command: ShellCommand,
        private val logFile: File
) : Executable {

    override fun execute(): Process {
        return LoggedProcess(logFile, BaseExecutable(command).execute())
    }
}