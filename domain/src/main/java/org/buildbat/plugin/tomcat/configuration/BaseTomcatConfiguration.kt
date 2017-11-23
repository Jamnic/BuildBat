package org.buildbat.plugin.tomcat.configuration

import org.buildbat.Future
import org.buildbat.LoggedFuture
import org.buildbat.execution.command.shell.ParametrizedShellCommand
import org.buildbat.execution.executable.LoggedExecutable
import org.buildbat.filesystem.file.ClasspathFile
import org.buildbat.filesystem.file.File
import org.buildbat.filesystem.file.WritableFile
import org.buildbat.json.JsonObject
import org.buildbat.log.Log
import org.buildbat.log.LogFactory
import org.buildbat.plugin.tomcat.TomcatContainers
import org.buildbat.plugin.tomcat.command.TomcatShellCommand
import org.buildbat.plugin.tomcat.container.EmptyTomcatContainer
import org.buildbat.plugin.tomcat.container.TomcatContainer
import org.buildbat.plugin.war.project.WarProject

class BaseTomcatConfiguration(
        private val name: String,
        private val tomcatContainer: TomcatContainer,
        private val port: String,
        private val log: LogFactory = LogFactory()
) : TomcatConfiguration {

    constructor(
            json: JsonObject
    ) : this(json["name"], json["tomcatContainer"], json["port"])

    constructor(
            name: String,
            tomcatContainerName: String,
            port: String
    ) : this(name, TomcatContainers().find(tomcatContainerName), port)

    override fun execute(command: String, project: WarProject): LoggedFuture<TomcatConfiguration> {
        val logFile = log.new(project.name())
        val log = Log(logFile)
        project.addLog(log)
        return LoggedFuture(
                log,
                Future({ TomcatShellCommand(command, tomcatContainer.bin()) })
                        .then { ParametrizedShellCommand(it, project) }
                        .then { ParametrizedShellCommand(it, tomcatContainer) }
                        .then { ParametrizedShellCommand(it, this) }
                        .then { LoggedExecutable(it, logFile) }
                        .then { it.execute() }
                        .then { this })
    }

    override fun container(): TomcatContainer {
        return tomcatContainer
    }

    override fun json(): JsonObject {
        return if (tomcatContainer is EmptyTomcatContainer)
            JsonObject(
                    "name" to name,
                    "port" to port)
        else
            JsonObject(
                    "name" to name,
                    "port" to port,
                    "tomcatContainer" to tomcatContainer.name(),
                    "serverXml" to if (tomcatContainer.conf().exists()) createServerXml().path() else "")
    }

    override fun name(): String {
        return name
    }

    private fun createServerXml(): File {
        return WritableFile(ClasspathFile("server.xml"))
                .copyTo(tomcatContainer.conf().file("server-$name.xml").create())
                .replace("8080", port)
                .replace("8443", "1" + port)
                .replace("8009", "2" + port)
                .replace("8005", "3" + port)
    }
}