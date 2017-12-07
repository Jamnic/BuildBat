package org.buildbat.core.execution.command.request

import org.buildbat.core.execution.command.shell.ShellCommand

/**
 * Command pattern used to postpone creation of {@link Command} objects.
 */
interface ShellCommandCreationCommand {
    fun createShellCommand(): ShellCommand
}