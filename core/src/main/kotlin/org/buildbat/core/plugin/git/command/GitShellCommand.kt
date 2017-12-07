package org.buildbat.core.plugin.git.command

import org.buildbat.core.execution.command.shell.ShellCommand
import org.buildbat.filesystem.directory.Directory

class GitShellCommand(
        command: String,
        executionDirectory: Directory
) : ShellCommand("git $command", executionDirectory)