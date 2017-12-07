package org.buildbat.core.plugin.xml

// TODO still working
open class XmlSelector(
        private val selector: String,
        private val attributes: String,
        private val content: String
) {

    fun content(): String {
        return content
    }

    fun selector(string: String): XmlSelector {
        return selector(string, content)
    }

    private fun selector(string: String, content: String): XmlSelector {

        if (content.contains("<$string")) {
            val toParse = content
                    .substringAfter("<$string")
                    .substringBefore("</$string>") + "</$string>"
            val attributes = toParse.substringBefore(">")
            val parsedContent = toParse.substringAfter(">").substringBefore("</$string>")
            return XmlSelector(
                    string,
                    attributes,
                    parsedContent)
        } else {
            return EmptyXmlSelector()
        }
    }

    fun selectMultiple(selector: String): List<XmlSelector> {
        val list = mutableListOf<XmlSelector>()
        var newContent = content
        var xmlselector: XmlSelector?
        while (newContent.isNotBlank()) {
            xmlselector = selector(selector, newContent)
            newContent = newContent.substringAfter(xmlselector.content + "</$selector>")
            list.add(xmlselector)
        }
        return list
    }

    override fun toString(): String {
        return "<$selector>...</$selector>"
    }
}