package tech.klopper.anagramus.service.io

import org.springframework.stereotype.Service
import tech.klopper.anagramus.model.WordModel
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.nio.charset.Charset

@Service
class DictionaryLoader : Dictionary {

    private var wordList: MutableList<WordModel> = mutableListOf()

    init {
        try {
            val inputStream = FileInputStream("words_alpha.txt")
            readIntoWords(inputStream)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }

    override fun getWordList(): List<WordModel> {
        return wordList
    }

    private fun readIntoWords(inputStream: FileInputStream) {
        val content = inputStream.readBytes().toString(Charset.defaultCharset())
        val wordListTmp = content.split('\n') as ArrayList<String>
        for (word in wordListTmp){
            wordList.add(WordModel(word))
        }
    }
}

interface Dictionary {
    fun getWordList(): List<WordModel>
}
