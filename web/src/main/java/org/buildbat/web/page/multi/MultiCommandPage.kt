package org.buildbat.web.page.multi

import org.buildbat.core.plugin.multi.MultiCommand
import org.buildbat.core.task.TaskPoolProvider
import org.buildbat.web.page.multi.request.MultiCommandRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/multi")
class MultiCommandPage {

    private val requestCommandMap = RequestCommandMap()
    private val multiCommand = MultiCommand()
    private val taskPool = TaskPoolProvider.INSTANCE.taskPool

    @PostMapping
    fun command(
            @RequestBody request: MultiCommandRequest
    ) {
        taskPool.add(
                multiCommand.execute(request.commandRequests.map { requestCommandMap[it] }))
    }
}