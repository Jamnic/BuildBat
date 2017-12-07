package org.buildbat.filesystem

class ClasspathLoader(
        private val path: String
) {

    private val classLoader = ClasspathLoader::class.java.classLoader

    fun get(): String {
        val resource = classLoader
                .getResource(path.substringAfter("classpath:"))

        return if (resource != null) resource.path else path
    }
}