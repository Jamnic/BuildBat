package org.buildbat.web.page.tomcat.response

class TomcatConfigurationInfoResponse(
        val name: String = "",
        val port: String = "",
        val serverXml: String = "",
        val container: TomcatContainerInfoResponse = TomcatContainerInfoResponse()
)