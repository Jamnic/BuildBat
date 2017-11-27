package org.buildbat.execution.command.shell

import org.buildbat.json.JsonEntity

class ParametrizedShellCommand(
        command: ShellCommand,
        parameterSource: JsonEntity
) : ShellCommand(ShellCommandParam(command.command(), parameterSource.json()).resolve(), command.executionDirectory())