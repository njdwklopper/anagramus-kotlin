package tech.klopper.anagramus.controller

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import tech.klopper.anagramus.model.WordModel
import tech.klopper.anagramus.service.io.Dictionary

@RestController
class DictionaryController(private val dictionary: Dictionary) {

    @RequestMapping(value = ["/dict/list"], method = [RequestMethod.GET])
    fun getDictionaryList(): List<WordModel> {
        return dictionary.getWordList()
    }

    @RequestMapping(value = ["/dict/exists/{word}"], method = [RequestMethod.GET])
    fun getDoesWordExist(@PathVariable word: String): Boolean {
        return dictionary.getWordList().contains(WordModel(word))
    }

    @RequestMapping(value = ["/dict/define/{word}"], method = [RequestMethod.GET])
    fun getWordGoogleDefinition(@PathVariable word: String): String? {
        return dictionary.getForObject(word, String::class.java)
    }
}
