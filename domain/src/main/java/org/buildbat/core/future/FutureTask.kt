package org.buildbat.core.future

interface FutureTask<out RESULT> {
    fun thread(): Thread
    fun <MAPPED_RESULT> then(mapFunction: (RESULT) -> MAPPED_RESULT): FutureTask<MAPPED_RESULT>
}