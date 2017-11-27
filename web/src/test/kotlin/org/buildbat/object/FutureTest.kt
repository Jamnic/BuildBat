package org.buildbat.`object`

import org.buildbat.core.future.Future
import org.buildbat.core.future.LoggedFuture
import org.buildbat.core.log.LogFile
import org.junit.Test

class FutureTest {

    @Test
    fun shouldWork() {
        Future({ "Print this" })
                .then { str -> str + " and that" }
                .resolve() { str -> println(str) }
    }

    @Test
    fun shouldFold() {
        val fold = listOf(LoggedFuture(LogFile("test"), Future({ println("First") })),
                LoggedFuture(LogFile("test"), Future({ println("Second") })),
                LoggedFuture(LogFile("test"), Future({ println("Third") })))
                .fold(
                        LoggedFuture(LogFile("test"), Future({ println("ZERO") })),
                        { acc, future -> acc.then(future) })

        fold.resolve()
    }
}