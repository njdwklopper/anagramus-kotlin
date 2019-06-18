package tech.klopper.anagramus.controller

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import tech.klopper.anagramus.model.WordModel
import tech.klopper.anagramus.service.io.Dictionary

class DictionaryListControllerTest {

    private lateinit var dictionaryListController: DictionaryListController
    @Before
    fun setUp() {
        val dict: Dictionary = MockDict(getTestList())
        dictionaryListController = DictionaryListController(dict)
    }

    @Test
    fun apiGetTests() {
        Assert.assertEquals(getTestList(), dictionaryListController.apiGetTests())
    }

    private fun getTestList(): MutableList<WordModel> {
        return mutableListOf(
                WordModel("TEST1"),
                WordModel("TEST2"),
                WordModel("TEST3"),
                WordModel("TEST4"),
                WordModel("TEST5"),
                WordModel("TEST6")
        )
    }
}

class MockDict(private val list: List<WordModel>) : Dictionary {
    override fun getWordList(): List<WordModel> {
        return list
    }
}
