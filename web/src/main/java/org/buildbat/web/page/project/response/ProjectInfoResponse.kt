package org.buildbat.web.page.project.response

import org.buildbat.core.future.FutureResult

class ProjectInfoResponse(
        val name: String = "",
        val directory: String = "",
        val logs: Collection<FutureResult> = listOf(),
        val params: Map<String, String> = mapOf()
)