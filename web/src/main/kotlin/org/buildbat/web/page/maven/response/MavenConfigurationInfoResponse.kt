package org.buildbat.web.page.maven.response

class MavenConfigurationInfoResponse(
        val name: String = "",
        val version: String = "",
        val home: String = "",
        val homeExists: Boolean = true
)