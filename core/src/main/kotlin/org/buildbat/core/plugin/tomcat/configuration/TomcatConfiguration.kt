package org.buildbat.core.plugin.tomcat.configuration

import org.buildbat.core.plugin.tomcat.container.TomcatContainer
import org.buildbat.json.JsonEntity

// TODO manageExtProperties application properties should be manageable from BuildBat.
// TODO deployFailedWars detect and redeploy failed wars

interface TomcatConfiguration : JsonEntity {
    fun tomcatContainer(): TomcatContainer
}