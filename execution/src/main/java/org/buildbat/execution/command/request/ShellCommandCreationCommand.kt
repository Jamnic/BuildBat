package org.buildbat.execution.command.request

import org.buildbat.execution.command.shell.ShellCommand

/**
 * Command pattern used to postpone creation of {@link Command} objects.
 */
interface ShellCommandCreationCommand {
    fun createShellCommand(): ShellCommand
}