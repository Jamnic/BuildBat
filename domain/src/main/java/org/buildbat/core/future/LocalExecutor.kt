package org.buildbat.core.future

import java.lang.Thread.sleep

class LocalExecutor(
        private val name: String,
        private val speed: Long
) : Executor {

    private var counter: Int = 0

    private var isFree: Boolean = true

    override fun isFree(): Boolean {
        return isFree
    }

    override fun execute(poll: (() -> Unit)) {
        this.isFree = false
        Thread(Runnable {
            val thread = Thread(Runnable({
                println("$name: Working...")
                poll.invoke()
                sleep(speed)
                ++counter
                println("$name: Done...")
            }))
            thread.start()
            thread.join()
            this.isFree = true
        }).start()
    }

    override fun counter(): Int {
        return counter
    }

    override fun name(): String {
        return name
    }
}