package org.buildbat.core.task.executor

import org.buildbat.core.future.FutureTask
import org.buildbat.core.task.TaskPoolProvider
import org.buildbat.json.BaseJsonEntity
import org.buildbat.json.JsonEntity
import org.buildbat.json.JsonObject

// TODO research Coroutines instead of Threads
class LocalExecutor(
        private val name: String,
        private val host: String,
        private val speed: String, // testing purposes
        private val jsonObject: JsonObject = JsonObject(
                "name" to name,
                "host" to host,
                "speed" to speed),
        private val json: JsonEntity = BaseJsonEntity(name, jsonObject)
) : Executor, JsonEntity by json {

    private val taskPool = TaskPoolProvider.INSTANCE.taskPool

    private var isFree: Boolean = true

    constructor(
            jsonObject: JsonObject
    ) : this(
            jsonObject["name"],
            jsonObject["host"],
            jsonObject["speed"],
            jsonObject)

    override fun notifyObserver() {
        tryToExecuteTask()
    }

    override fun host(): String {
        return host
    }

    private fun tryToExecuteTask() {
        Thread(Runnable {
            if (isFree && taskPool.isNotEmpty()) {

                isFree = false
                execute(taskPool.get())
                isFree = true

                tryToExecuteTask()
            }
        }).start()
    }

    private fun execute(task: FutureTask<Any>) {
        val thread = task.thread()
        thread.start()
        thread.join()
    }
}