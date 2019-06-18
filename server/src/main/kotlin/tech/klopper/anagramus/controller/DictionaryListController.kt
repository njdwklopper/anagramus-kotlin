package tech.klopper.anagramus.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import tech.klopper.anagramus.model.WordModel
import tech.klopper.anagramus.service.io.Dictionary


@RestController
class DictionaryListController(private val dictionary: Dictionary) {

    @RequestMapping(value = ["/dict/list"], method = [RequestMethod.GET])
    fun apiGetTests(): List<WordModel> {
        return dictionary.getWordList()
    }
}
