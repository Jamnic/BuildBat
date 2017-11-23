package org.buildbat.web.page.tomcat.request

class NewTomcatConfigurationRequest(
        val name: String = "",
        val tomcatContainerName: String = "",
        val port: String = ""
)