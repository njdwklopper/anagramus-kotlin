package tech.klopper.anagramus.controller

import org.junit.Before
import org.junit.Test
import tech.klopper.anagramus.service.io.Dictionary

class WordControllerTest {

    private lateinit var wordController: WordController
    @Before
    fun setUp() {
        val dict: Dictionary = MockDict(getTestList())
        wordController = WordController(dict)
    }

    @Test
    fun getWordsFromWord() {
        val list = getTestList()
        val wordsFromWord = wordController.getWordsFromWord(list[0])
        assert(wordsFromWord!!.contains(list[1]))
        assert(wordsFromWord.contains(list[2]))
        assert(wordsFromWord.contains(list[3]))
        assert(wordsFromWord.contains(list[4]))
        assert(wordsFromWord.contains(list[5]))
    }

    private fun getTestList(): MutableList<String> {
        return mutableListOf(
            "wordtest",
            "sore",
            "set",
            "row",
            "sow",
            "sew"
        )
    }
}
