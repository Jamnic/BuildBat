package org.buildbat.plugin.tomcat.command

import org.buildbat.execution.command.shell.ShellCommand
import org.buildbat.filesystem.directory.Directory

class TomcatShellCommand(
        command: String,
        executionDirectory: Directory
) : ShellCommand(command, executionDirectory)
