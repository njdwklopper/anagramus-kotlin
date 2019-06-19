package tech.klopper.anagramus.controller

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import tech.klopper.anagramus.service.io.Dictionary

class DictionaryControllerTest {

    private lateinit var dictionaryController: DictionaryController
    @Before
    fun setUp() {
        val dict: Dictionary = MockDict(getTestList())
        dictionaryController = DictionaryController(dict)
    }

    @Test
    fun test_dictionary_list() {
        Assert.assertEquals(getTestList(), dictionaryController.getDictionaryList())
    }

    @Test
    fun test_does_word_exist() {
        val list = getTestList()
        for (word in list) {
            assert(dictionaryController.getDoesWordExist(word))
        }
    }

    @Test
    fun test_google_word_definition() {
        val list = getTestList()
        for (word in list) {
            Assert.assertEquals(word, dictionaryController.getWordGoogleDefinition(word))
        }
    }

    private fun getTestList(): MutableList<String> {
        return mutableListOf(
                "TEST1",
                "TEST2",
                "TEST3",
                "TEST4",
                "TEST5",
                "TEST6"
        )
    }
}

class MockDict(private val list: List<String>) : Dictionary {
    override fun <T> getForObject(word: String, responseType: Class<T>, vararg uriVariables: Any): String? {
        return word
    }

    override fun getWordList(): List<String> {
        return list
    }
}
