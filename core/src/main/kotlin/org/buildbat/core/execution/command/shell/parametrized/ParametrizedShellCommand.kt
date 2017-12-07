package org.buildbat.core.execution.command.shell.parametrized

import org.buildbat.core.execution.command.shell.ShellCommand
import org.buildbat.json.JsonEntity

class ParametrizedShellCommand(
        command: ShellCommand,
        parameterSource: JsonEntity
) : ShellCommand(
        ShellCommandParameter(
                command.command(),
                parameterSource.json())
                .resolve(),
        command.executionDirectory())