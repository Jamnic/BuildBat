package org.buildbat.execution.command.shell

import org.buildbat.json.Json

class ParametrizedShellCommand(
        command: ShellCommand,
        parameterSource: Json
) : ShellCommand(ShellCommandParam(command.command(), parameterSource.json()).resolve(), command.executionDirectory())