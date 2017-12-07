package org.buildbat.core.execution.command.shell.submodule

import org.buildbat.core.execution.command.shell.ShellCommand
import org.buildbat.core.plugin.project.multimodule.MultiModuleProject

class SubmoduleParametrizedShellCommand(
        command: ShellCommand,
        parameterSource: MultiModuleProject
) : ShellCommand(
        ShellCommandSubmoduleParameter(
                command.command(),
                parameterSource)
                .resolve(),
        command.executionDirectory())