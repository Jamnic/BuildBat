package org.buildbat

import org.buildbat.log.Log

class LoggedFuture<out RESULT>(
        val log: Log,
        val future: Future<RESULT>
) {

    fun <MAPPED_RESULT> map(function: (RESULT) -> MAPPED_RESULT): LoggedFuture<MAPPED_RESULT> {
        val mapped = future.then(function)
        return LoggedFuture(log, mapped)
    }

    fun <MAPPED_RESULT> map(newFuture: LoggedFuture<MAPPED_RESULT>): LoggedFuture<MAPPED_RESULT> {
        val mapped = future.then(newFuture.future)
        return LoggedFuture(log, mapped)
    }

    fun resolve(): LogResponse {
        future.resolve()
        return LogResponse(log.file())
    }

    fun resolve(function: (RESULT) -> Unit): LogResponse {
        future.doWhenResolved(function)
        return LogResponse(log.file())
    }
}