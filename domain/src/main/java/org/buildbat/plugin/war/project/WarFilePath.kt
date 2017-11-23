package org.buildbat.plugin.war.project

import org.buildbat.project.Project

class WarFilePath(
        private val project: Project,
        private val version: String,
        private val artifactId: String
) {
    fun get(): String {
        return "${project.directory().path()}target\\$artifactId-$version.war"
    }
}