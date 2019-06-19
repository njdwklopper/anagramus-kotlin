package tech.klopper.word.helper

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import tech.klopper.word.io.WordResourceLoader
import tech.klopper.word.io.WordLoaderStub

class WordHelperTest {

    private lateinit var assetLoaderWord: WordResourceLoader
    private lateinit var wordHelper: WordHelper

    @Before
    @Throws(Exception::class)
    fun before() {
        assetLoaderWord = WordLoaderStub()
        wordHelper = WordHelper(assetLoaderWord)
    }

    @Test
    @Throws(Exception::class)
    fun test_ShuffleString() {
        assertNotSame("test", wordHelper.shuffleString("test"))
        assertNotSame("soup", wordHelper.shuffleString("soup"))
        assertNotSame("super", wordHelper.shuffleString("super"))
        assertNotSame("striker", wordHelper.shuffleString("striker"))
    }

    @Test
    @Throws(Exception::class)
    fun test_getAvailableWordsFromAWord() {
        val matchingStringCharSet = wordHelper.getAvailableWordsFromWord("superb")
        for (word in matchingStringCharSet){
            println(word)
        }
        assert(matchingStringCharSet.contains("sue"))
        assert(matchingStringCharSet.contains("super"))
        assert(matchingStringCharSet.contains("use"))
        assert(matchingStringCharSet.contains("user"))
    }

    @Test
    @Throws(Exception::class)
    fun test_getWordsMatchingStringCharSet() {
        val matchingStringCharSet = wordHelper.getMatchingWordsByLength("superb", 3)
        assert(matchingStringCharSet.contains("sup"))
        assert(matchingStringCharSet.contains("sue"))
        assert(matchingStringCharSet.contains("use"))
        assert(matchingStringCharSet.contains("bus"))
    }

    @Test
    @Throws(Exception::class)
    fun test_ShuffleStringIsTheSame() {
        assertNotSame("test", wordHelper.isShuffledStringAccidentallyTheWord("test", "test"))
        assertEquals("tester", wordHelper.isShuffledStringAccidentallyTheWord("test", "tester"))
    }

    @Test
    @Throws(Exception::class)
    fun test_DoesWordContainThisManyChars() {
        assertTrue(wordHelper.doesWordContainThisManyChars("test", 4))
        assertTrue(wordHelper.doesWordContainThisManyChars("special", 7))
        assertTrue(wordHelper.doesWordContainThisManyChars("too", 3))
        assertFalse(wordHelper.doesWordContainThisManyChars("test", 3))
        assertFalse(wordHelper.doesWordContainThisManyChars("special", 4))
        assertFalse(wordHelper.doesWordContainThisManyChars("too", 0))
    }

    @Test
    @Throws(Exception::class)
    fun test_DoesCharacterMatchInsideWords() {
        assertTrue(wordHelper.doesCharacterMatchInsideWords('c', "chow"))
        assertTrue(wordHelper.doesCharacterMatchInsideWords('h', "chow"))
        assertTrue(wordHelper.doesCharacterMatchInsideWords('o', "chow"))
        assertTrue(wordHelper.doesCharacterMatchInsideWords('w', "chow"))
        assertFalse(wordHelper.doesCharacterMatchInsideWords('z', "chow"))
        assertFalse(wordHelper.doesCharacterMatchInsideWords('t', "chow"))
        assertFalse(wordHelper.doesCharacterMatchInsideWords('p', "chow"))
    }

    @Test
    @Throws(Exception::class)
    fun test_GetTemporaryWordListbySize() {
        var tmpList = wordHelper.getWordListByWordLength(3)
        for (word in tmpList) {
            assertTrue(word.length == 3)
        }
        tmpList = wordHelper.getWordListByWordLength(4)
        for (word in tmpList) {
            assertTrue(word.length == 4)
        }
        tmpList = wordHelper.getWordListByWordLength(5)
        for (word in tmpList) {
            assertTrue(word.length == 5)
        }
        tmpList = wordHelper.getWordListByWordLength(6)
        for (word in tmpList) {
            assertTrue(word.length == 6)
        }
        assertEquals(15290, tmpList.size)
        tmpList = wordHelper.getWordListByWordLength(7)
        assertEquals(0, tmpList.size)
        for (word in tmpList) {
            assertTrue(word.length == 7)
        }
    }

    @Test
    @Throws(Exception::class)
    fun test_RemoveCharacter() {
        var testString = "whatever"
        assertEquals(
                "whatver",
                wordHelper.removeCharacter(testString, 'e')
        )
        assertEquals(
                "whtever",
                wordHelper.removeCharacter(testString, 'a')
        )
        assertEquals(
                "watever",
                wordHelper.removeCharacter(testString, 'h')
        )
        testString = "wtver"
        assertEquals(
                "wtvr",
                wordHelper.removeCharacter(testString, 'e')
        )
        testString = "wtvr"
        assertEquals(
                "wtv",
                wordHelper.removeCharacter(testString, 'r')
        )
    }
}
