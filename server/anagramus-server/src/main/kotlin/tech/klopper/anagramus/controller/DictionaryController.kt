package tech.klopper.anagramus.controller

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import tech.klopper.anagramus.service.io.Dictionary

@RestController
@RequestMapping("/dict")
class DictionaryController(private val dictionary: Dictionary) {

    @RequestMapping(value = ["/list"], method = [RequestMethod.GET])
    fun getDictionaryList(): List<String> {
        return dictionary.getWordList()
    }

    @RequestMapping(value = ["/exists/{word}"], method = [RequestMethod.GET])
    fun getDoesWordExist(@PathVariable word: String): Boolean {
        return dictionary.getWordList().contains(word)
    }

    @RequestMapping(value = ["/define/{word}"], method = [RequestMethod.GET])
    fun getWordGoogleDefinition(@PathVariable word: String): String? {
        return dictionary.getForObject(word, String::class.java)
    }
}
