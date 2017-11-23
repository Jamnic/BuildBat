package org.buildbat.plugin.tomcat.configuration

import org.buildbat.LoggedFuture
import org.buildbat.json.Json
import org.buildbat.plugin.tomcat.container.TomcatContainer
import org.buildbat.plugin.war.project.WarProject

// TODO manageExtProperties application properties should be manageable from BuildBat.
// TODO deployFailedWars detect and redeploy failed wars

interface TomcatConfiguration : Json {
    fun execute(command: String, project: WarProject): LoggedFuture<TomcatConfiguration>
    fun container(): TomcatContainer
}