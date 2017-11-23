package org.buildbat.web.page.maven.request

class NewMavenConfigurationRequest(
        val name: String = "",
        val version: String = "",
        val home: String = ""
)