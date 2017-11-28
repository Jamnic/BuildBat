package org.buildbat.core.plugin.maven.command

import org.buildbat.core.plugin.maven.configuration.MavenConfiguration
import org.buildbat.core.plugin.maven.project.MavenProject
import org.buildbat.execution.command.shell.ShellCommand
import org.buildbat.filesystem.directory.Directory

class MavenShellCommand(
        command: String,
        mavenConfiguration: MavenConfiguration,
        executionDirectory: Directory
) : ShellCommand("${mavenConfiguration.mvnExecutable().executable()} $command", executionDirectory) {

    constructor(
            command: String, mavenProject: MavenProject
    ) : this(command, mavenProject.mavenConfiguration(), mavenProject.directory())
}