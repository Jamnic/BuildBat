package org.buildbat.execution.process

import java.io.InputStream

interface Process {
    fun readInputStream(): InputStream
    fun alive(): Boolean
    fun success(): Boolean
}





