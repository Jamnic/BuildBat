package org.buildbat.web.page.multi.request

import org.buildbat.web.request.ExecutionRequest

class MultiCommandRequest(
        val commandRequests: List<ExecutionRequest> = listOf()
)