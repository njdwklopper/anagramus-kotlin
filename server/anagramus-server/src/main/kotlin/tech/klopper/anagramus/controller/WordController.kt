package tech.klopper.anagramus.controller

import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import tech.klopper.anagramus.service.io.Dictionary
import tech.klopper.word.helper.IWordHelper
import tech.klopper.word.helper.WordHelper

@RestController
@RequestMapping("/word")
class WordController(dictionary: Dictionary) {
    private val wordHelperImpl = WordHelper(dictionary)

    @RequestMapping(value = ["/list/from/{word}"], method = [RequestMethod.GET])
    fun getWordsFromWord(@PathVariable word: String): List<String>? {
        return wordHelperImpl.getAvailableWordsFromWord(word)
    }
}

@Component
abstract class WordHelper : IWordHelper
