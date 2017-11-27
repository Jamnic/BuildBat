package org.buildbat.core.future

import org.buildbat.core.log.LogFile

class LoggedFuture<out RESULT>(
        val logFile: LogFile,
        val future: Future<RESULT>
) {

    fun resolve(function: (RESULT) -> Unit): FutureResult {
        future.resolve(function)
        return FutureResult(logFile)
    }

    fun resolve(): FutureResult {
        future.resolve()
        return FutureResult(logFile)
    }

    fun <MAPPED_RESULT> then(function: (RESULT) -> MAPPED_RESULT): LoggedFuture<MAPPED_RESULT> {
        val mapped = future.then(function)
        return LoggedFuture(logFile, mapped)
    }

    fun <MAPPED_RESULT> then(newFuture: LoggedFuture<MAPPED_RESULT>): LoggedFuture<MAPPED_RESULT> {
        val mapped = future.then(newFuture.future)
        return LoggedFuture(logFile, mapped)
    }
}