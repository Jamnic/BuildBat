package org.buildbat.plugin.maven.command

import org.buildbat.execution.command.shell.ShellCommand
import org.buildbat.filesystem.directory.Directory
import org.buildbat.plugin.maven.configuration.MavenConfiguration
import org.buildbat.plugin.maven.project.MavenProject

class MavenShellCommand(
        command: String,
        mavenConfiguration: MavenConfiguration,
        executionDirectory: Directory
) : ShellCommand("${mavenConfiguration.mvn()} $command", executionDirectory) {

    constructor(
            command: String, mavenProject: MavenProject
    ) : this(command, mavenProject.mavenConfig(), mavenProject.directory())
}