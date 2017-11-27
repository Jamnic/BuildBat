package org.buildbat.json

import kotlin.Pair
import org.buildbat.core.file.JsonFile
import org.buildbat.filesystem.file.BaseFile
import spock.lang.Specification

class JsonSpec extends Specification {

    def "should read json array from file"() {
        given:
          def file = new JsonFile(new BaseFile("src/test/resources/test.json"))
        when:
          def array = file.jsonArray("name")
        then:
          array.list().size() == 4
    }

    def "should read json array from file and find specified json object"() {
        given:
          def file = new JsonFile(new BaseFile("src/test/resources/test.json"))
          def array = file.jsonArray("name")
        when:
          def jsonObject = array.find("First")
        then:
          jsonObject["name"] == "First"
    }

    def "should read json array from file and not find any json"() {
        given:
          def file = new JsonFile(new BaseFile("src/test/resources/test.json"))
          def array = file.jsonArray("name")
        when:
          def jsonObject = array.find("Other")
        then:
          jsonObject["name"] == ""
    }

    def "should read json array from file and add new json"() {
        given:
          def file = new JsonFile(new BaseFile("src/test/resources/test.json"))
          def array = file.jsonArray("name")
        when:
          def newArray = array.save(new JsonObject(
                  new Pair<String, String>("name", "Third")))
          def jsonObject = newArray.find("Third")
        then:
          jsonObject["name"] == "Third"
          newArray.list().size() == 5
    }

    def "should read json array from file and replace new json"() {
        given:
          def file = new JsonFile(new BaseFile("src/test/resources/test.json"))
          def array = file.jsonArray("name")
        when:
          def newArray = array.save(new JsonObject(
                  new Pair<String, String>("name", "First"),
                  new Pair<String, String>("content", "Brand new content")))
          def jsonObject = newArray.find("First")
        then:
          jsonObject["name"] == "First"
          jsonObject["content"] == "Brand new content"
          newArray.list().size() == 4
    }

    def "should read json array from file and remove json"() {
        given:
          def file = new JsonFile(new BaseFile("src/test/resources/test.json"))
          def array = file.jsonArray("name")
        when:
          def newArray = array.remove(new JsonObject(
                  new Pair<String, String>("name", "First")))
        then:
          newArray.list().size() == 3
    }
}
