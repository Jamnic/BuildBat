package org.buildbat.project

import org.buildbat.Future
import org.buildbat.execution.command.shell.ParametrizedShellCommand
import org.buildbat.execution.executable.LoggedExecutable
import org.buildbat.filesystem.directory.Directory
import org.buildbat.json.JsonObject
import org.buildbat.log.Log
import org.buildbat.log.LogFactory
import org.buildbat.log.LogHistory
import org.buildbat.plugin.cmd.command.CmdShellCommand

class BaseProject(
        private val name: String,
        private val directory: Directory,
        private val log: LogFactory = LogFactory(),
        private val logHistory: LogHistory = LogHistory(name),
        private val json: JsonObject = JsonObject("name" to name, "directory" to directory.path())
) : Project {

    constructor(json: JsonObject) : this(json["name"], json["directory"], json)
    constructor(name: String, directory: String, json: JsonObject) : this(name, Directory(directory), json = json)

    override fun addLog(log: Log) {
        logHistory.add(log)
    }

    override fun logs(): Collection<Log> {
        return logHistory.logs()
    }

    override fun directory(): Directory {
        return directory
    }

    override fun name(): String {
        return name
    }

    override fun json(): JsonObject {
        return json.add(
                "pathName" to name.replace(" ", "_"))
    }

    // TODO move somewhere else
    override fun cmd(command: String): Future<Project> {
        val logFile = log.new(name)
        return Future({ CmdShellCommand(command, this.directory()) })
                .then { ParametrizedShellCommand(it, this) }
                .then { LoggedExecutable(it, logFile) }
                .then { this }
    }
}