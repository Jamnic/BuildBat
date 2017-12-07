package org.buildbat.web.page.project.request

class AddProjectRequest(
        val directory: String = "",
        val customParams: Map<String, String> = mapOf()
)