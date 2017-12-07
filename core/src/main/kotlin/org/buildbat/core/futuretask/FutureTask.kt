package org.buildbat.core.futuretask

interface FutureTask<out RESULT> {
    fun thread(): Thread
    fun execute(): () -> RESULT
    fun <MAPPED_RESULT> then(mapFunction: (RESULT) -> MAPPED_RESULT): FutureTask<MAPPED_RESULT>
}