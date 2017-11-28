package org.buildbat.core.future

class BaseFutureTask<out RESULT>(
        private val functionToRunInFuture: () -> RESULT
) : FutureTask<RESULT> {

    override fun thread(): Thread {
        return Thread(Runnable { functionToRunInFuture() })
    }

    override fun <NEW_RESULT> then(mapFunction: (RESULT) -> NEW_RESULT): FutureTask<NEW_RESULT> {
        return BaseFutureTask({ mapFunction.invoke(functionToRunInFuture()) })
    }
}