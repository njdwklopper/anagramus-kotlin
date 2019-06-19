package tech.klopper.word.io

import java.io.File
import java.io.FileNotFoundException

class WordLoaderStub : WordResourceLoader {

    private var wordList: MutableList<String> = mutableListOf()

    init {
        try {
            val wordListTmp = File(javaClass.getResource("/test_word.list").file).useLines { it.toList() }
            for (word in wordListTmp) {
                wordList.add(word)
            }
        } catch (e: FileNotFoundException) {
            //TODO Handle exception
            e.printStackTrace()
        }
    }

    override fun getWordList(): List<String> {
        return wordList
    }
}
