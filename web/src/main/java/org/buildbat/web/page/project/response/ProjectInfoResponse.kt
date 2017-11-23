package org.buildbat.web.page.project.response

import org.buildbat.LogResponse

class ProjectInfoResponse(
        val name: String = "",
        val directory: String = "",
        val logs: Collection<LogResponse> = listOf(),
        val params: Map<String, String> = mapOf()
)