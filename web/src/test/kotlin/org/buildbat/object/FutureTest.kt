package org.buildbat.`object`

import org.buildbat.`object`.file.File
import org.junit.Test

class FutureTest {

    @Test
    fun shouldWork() {
        Future({ "Print this" })
                .map { str -> str + " and that" }
                .doWhenResolved { str -> println(str) }
    }

    @Test
    fun shouldFold() {
        val fold = listOf(LoggedFuture(File("test"), Future({ println("First") })),
                LoggedFuture(File("test"), Future({ println("Second") })),
                LoggedFuture(File("test"), Future({ println("Third") })))
                .fold(
                        LoggedFuture(File("test"), Future({ println("ZERO") })),
                        {acc, future -> acc.map(future)})

        fold.resolve()
    }
}