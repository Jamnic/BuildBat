package org.buildbat.core.execution.command.shell

import org.buildbat.filesystem.directory.Directory

/**
 * Command pattern used to postpone execution of shell command code
 */
open class ShellCommand(
        private val command: String,
        private val executionDirectory: Directory
) {

    open fun command(): String {
        return command
    }

    fun executionDirectory(): Directory {
        return executionDirectory
    }
}