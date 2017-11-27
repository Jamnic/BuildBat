package org.buildbat.`object`

import org.buildbat.core.future.Executor
import org.buildbat.core.future.ExecutorPool
import org.buildbat.core.future.LocalExecutor
import org.junit.Test

class ExecutorPoolTest {

    @Test
    fun shouldTest() {

        val tasks = setOf(
                { println("1 task") },
                { println("2 task") },
                { println("3 task") },
                { println("4 task") },
                { println("5 task") },
                { println("6 task") },
                { println("7 task") },
                { println("8 task") },
                { println("9 task") },
                { println("10 task") },
                { println("11 task") },
                { println("12 task") },
                { println("13 task") },
                { println("14 task") },
                { println("15 task") },
                { println("16 task") },
                { println("17 task") },
                { println("18 task") },
                { println("19 task") },
                { println("20 task") },
                { println("21 task") },
                { println("22 task") },
                { println("23 task") },
                { println("24 task") },
                { println("25 task") },
                { println("26 task") }
        )

        val executors: MutableList<Executor> = mutableListOf(
                LocalExecutor("Ann", 500),
                LocalExecutor("Bob", 1000),
                LocalExecutor("Cynthia", 1200),
                LocalExecutor("Daniel", 800),
                LocalExecutor("Frodo", 200),
                LocalExecutor("Greta", 400),
                LocalExecutor("Hilda", 400),
                LocalExecutor("Ian", 400),
                LocalExecutor("Jan", 1000)
        )
        val executorPool = ExecutorPool(executors) // 3631

        val millis = System.currentTimeMillis()
        executorPool
                .execute(tasks)
        println(System.currentTimeMillis() - millis)

        executors.forEach { println(it.name() + " " + it.counter()) }
        println("total tasks consumed: " + executors.fold(0, { acc, ex -> acc + ex.counter() }))
        println("task to perform: " + tasks.size)
    }

}