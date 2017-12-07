package org.buildbat.web.page.project.response

class ProjectInfoResponse(
        val name: String = "",
        val directory: String = "",
        val logs: Collection<LogInfoResponse> = listOf(),
        val params: Map<String, String> = mapOf()
)