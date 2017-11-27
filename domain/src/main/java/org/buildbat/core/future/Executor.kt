package org.buildbat.core.future

interface Executor {
    fun isFree(): Boolean
    fun execute(poll: (() -> Unit))
    fun counter() : Int
    fun name() : String
}