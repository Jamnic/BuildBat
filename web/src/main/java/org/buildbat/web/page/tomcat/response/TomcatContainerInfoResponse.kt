package org.buildbat.web.page.tomcat.response

class TomcatContainerInfoResponse(
        val name: String = "",
        val home: String = "",
        val containerExists: Boolean = true
)