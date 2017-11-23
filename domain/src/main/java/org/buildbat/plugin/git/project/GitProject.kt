package org.buildbat.plugin.git.project

import org.buildbat.project.Project

interface GitProject : Project {
    fun repository(): String
}