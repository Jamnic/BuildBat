package org.buildbat.core.plugin.cmd.command

import org.buildbat.execution.command.shell.ShellCommand
import org.buildbat.filesystem.directory.Directory

// TODO - bad design - works only for windows at the moment
class CmdShellCommand(
        command: String,
        executionDirectory: Directory
) : ShellCommand("cmd.exe /c $command", executionDirectory)