package org.buildbat.core.task.executor

import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import org.buildbat.core.future.FutureTask
import org.buildbat.core.task.TaskPoolProvider
import org.buildbat.json.BaseJsonEntity
import org.buildbat.json.JsonEntity
import org.buildbat.json.JsonObject

// TODO research Coroutines instead of Threads
class LocalExecutor(
        private val name: String,
        private val host: String,
        private val jsonObject: JsonObject = JsonObject(
                "name" to name,
                "host" to host),
        private val json: JsonEntity = BaseJsonEntity(name, jsonObject)
) : Executor, JsonEntity by json {

    private val taskPool = TaskPoolProvider.taskPool

    private var isFree: Boolean = true

    constructor(
            jsonObject: JsonObject
    ) : this(
            jsonObject["name"],
            jsonObject["host"],
            jsonObject)

    override fun notifyObserver() {
        tryToExecuteTask()
    }

    override fun host(): String {
        return host
    }

    override fun isFree(): Boolean {
        return isFree
    }

//    private fun tryToExecuteTask() {
//        Thread(Runnable {
//            val thread = Thread(Runnable {
//                if (isFree && taskPool.isNotEmpty()) {
//                    isFree = false
//                    taskPool.get().execute().invoke()
//                    isFree = true
//
//                    tryToExecuteTask()
//                }
//            })
//            thread.start()
//            thread.join()
//        }).start()
//    }

    private fun tryToExecuteTask() {
        runBlocking {
            if (isFree && taskPool.isNotEmpty()) {
                isFree = false
                async {
                    (taskPool.get()).execute().invoke()
                }.await()
                isFree = true
                tryToExecuteTask()
            }
        }
    }
}