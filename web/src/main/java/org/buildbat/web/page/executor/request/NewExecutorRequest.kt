package org.buildbat.web.page.executor.request

class NewExecutorRequest(
        val name: String = "",
        val host: String = "",
        val speed: String = "" // TODO testing latency
)