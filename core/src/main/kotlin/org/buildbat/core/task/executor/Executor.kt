package org.buildbat.core.task.executor

import org.buildbat.core.task.TaskPoolObserver
import org.buildbat.json.JsonEntity

interface Executor : TaskPoolObserver, JsonEntity {
    fun host(): String
    fun isFree() : Boolean
}