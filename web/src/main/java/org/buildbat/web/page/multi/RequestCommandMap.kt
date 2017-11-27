package org.buildbat.web.page.multi

import org.buildbat.execution.command.request.ShellCommandCreationCommand
import org.buildbat.core.plugin.cmd.command.CmdShellCommandCreationCommand
import org.buildbat.core.plugin.git.command.GitShellCommandCreationCommand
import org.buildbat.core.plugin.maven.command.MavenShellCommandCreationCommand
import org.buildbat.core.plugin.tomcat.command.TomcatShellCommandCreationCommand
import org.buildbat.web.page.git.request.GitExecutionRequest
import org.buildbat.web.page.maven.request.MavenExecutionRequest
import org.buildbat.web.page.tomcat.request.TomcatExecutionRequest
import org.buildbat.web.request.ExecutionRequest

class RequestCommandMap {

    operator fun get(request: ExecutionRequest): ShellCommandCreationCommand {
        return when (request) {
            is TomcatExecutionRequest ->
                TomcatShellCommandCreationCommand(request.command, request.projectName, request.tomcatConfiguration)
            is MavenExecutionRequest ->
                MavenShellCommandCreationCommand(request.command, request.projectName)
            is GitExecutionRequest ->
                GitShellCommandCreationCommand(request.command, request.projectName)
            else ->
                CmdShellCommandCreationCommand(request.command, request.projectName)
        }
    }
}