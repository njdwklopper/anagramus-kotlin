package tech.klopper.anagramus.service.io

import org.springframework.context.annotation.Configuration
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.nio.charset.Charset

@Configuration
class DictionaryLoader : Dictionary {

    private var wordLists: List<String> = ArrayList(120000)

    init {
        try {
            val inputStream = FileInputStream("words_alpha.txt")
            readIntoWords(inputStream)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }

    override fun getWordList(): List<String> {
        return wordLists
    }

    private fun readIntoWords(inputStream: FileInputStream) {
        val content = inputStream.readBytes().toString(Charset.defaultCharset())
        wordLists = content.split('\n') as ArrayList<String>
    }
}

interface Dictionary {
    fun getWordList(): List<String>
}
