package org.buildbat.core.plugin.tomcat.configuration

import org.buildbat.core.plugin.tomcat.ServerXmlFile
import org.buildbat.core.plugin.tomcat.container.TomcatContainer
import org.buildbat.core.plugin.tomcat.container.TomcatContainers
import org.buildbat.json.BaseJsonEntity
import org.buildbat.json.JsonEntity
import org.buildbat.json.JsonObject

// TODO something is wrong here
class BaseTomcatConfiguration(
        name: String,
        tomcatContainerName: String,
        port: String,
        serverXmlFile: ServerXmlFile = ServerXmlFile(name, port),
        jsonObject: JsonObject = JsonObject(
                "name" to name,
                "tomcatContainer" to tomcatContainerName,
                "port" to port,
                "serverXml" to serverXmlFile.path()),
        json: JsonEntity = BaseJsonEntity(name, jsonObject)
) : TomcatConfiguration, JsonEntity by json {

    private val tomcatContainer: TomcatContainer by lazy { TomcatContainers().find(tomcatContainerName) }

    constructor(
            jsonObject: JsonObject
    ) : this(
            jsonObject["name"],
            jsonObject["tomcatContainer"],
            jsonObject["port"],
            ServerXmlFile(jsonObject["name"], jsonObject["port"]),
            jsonObject)

    override fun tomcatContainer(): TomcatContainer {
        return tomcatContainer
    }
}