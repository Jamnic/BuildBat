package org.buildbat.web.page.multi

import org.buildbat.core.future.FutureResult
import org.buildbat.core.plugin.multi.MultiCommand
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

    @PostMapping
    fun command(
            @RequestBody request: MultiCommandRequest
    ): FutureResult {
        return multiCommand
                .execute(request.commandRequests.map { requestCommandMap[it] })
                .resolve()
    }
}