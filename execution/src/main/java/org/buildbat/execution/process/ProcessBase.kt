package org.buildbat.execution.process

import java.io.InputStream

class ProcessBase(
        private val process: java.lang.Process
) : Process {

    override fun readInputStream(): InputStream {
        return process.inputStream
    }

    override fun alive(): Boolean {
        return process.isAlive
    }

    override fun success(): Boolean {
        return process.exitValue() == 0
    }
}