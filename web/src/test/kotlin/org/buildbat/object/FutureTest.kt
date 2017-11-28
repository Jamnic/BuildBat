package org.buildbat.`object`

import org.buildbat.core.future.BaseFutureTask
import org.junit.Test

class FutureTest {

    @Test
    fun shouldWork() {
        BaseFutureTask({ "Print this" })
                .then { str -> str + " and that" }
                .thread()
    }
}