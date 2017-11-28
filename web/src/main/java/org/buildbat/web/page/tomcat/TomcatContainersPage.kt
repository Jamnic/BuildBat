package org.buildbat.web.page.tomcat

import org.buildbat.core.plugin.tomcat.container.BaseTomcatContainer
import org.buildbat.core.plugin.tomcat.container.TomcatContainers
import org.buildbat.web.page.tomcat.request.NewTomcatContainerRequest
import org.buildbat.web.page.tomcat.response.TomcatContainerInfoResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tomcat/container")
class TomcatContainersPage {

    private val tomcatContainers = TomcatContainers()

    @PutMapping
    fun put(@RequestBody request: NewTomcatContainerRequest) {
        tomcatContainers
                .save(BaseTomcatContainer(request.name, request.home))
    }

    @GetMapping
    fun get(): List<TomcatContainerInfoResponse> {
        return tomcatContainers
                .list()
                .map { configuration -> TomcatContainerInfoResponse(configuration.key()) }
    }
}