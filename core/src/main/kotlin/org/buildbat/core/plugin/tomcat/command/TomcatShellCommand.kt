package org.buildbat.core.plugin.tomcat.command

import org.buildbat.core.execution.command.shell.ShellCommand
import org.buildbat.filesystem.directory.Directory

class TomcatShellCommand(
        command: String,
        tomcatHome: Directory
) : ShellCommand(command, tomcatHome.cd("bin"))
