package tech.klopper.anagramus.service.io

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service
import tech.klopper.anagramus.model.WordModel
import java.io.File
import java.io.FileNotFoundException

@Service
@Configuration
class DictionaryLoader(
        @Value("\${tech.klopper.anagramus.dictionary.path}") private val dictPath: String
) : Dictionary {

    private var wordList: MutableList<WordModel> = mutableListOf()

    init {
        try {
            val wordListTmp = File(dictPath).useLines { it.toList() }
            for (word in wordListTmp) {
                wordList.add(WordModel(word))
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }

    override fun getWordList(): List<WordModel> {
        return wordList
    }
}

interface Dictionary {
    fun getWordList(): List<WordModel>
}
