package tech.klopper.word.helper

import tech.klopper.word.io.WordResourceLoader
import java.util.*
import java.util.regex.Pattern

interface IWordHelper {
    fun getMatchingWordsByLength(string: String, wordSize: Int): ArrayList<String>
    fun getAvailableWordsFromWord(wordIn: String): List<String>
    fun getWordListByWordLength(length: Int): List<String>
}

class WordHelper(private val wordLoaderWord: WordResourceLoader) : IWordHelper {

    fun doesWordContainThisManyChars(word: String, length: Int): Boolean {
        return word.length == length
    }

    override fun getMatchingWordsByLength(string: String, wordSize: Int): ArrayList<String> {
        val tmpList = getWordListByWordLength(wordSize)
        val newList = ArrayList<String>()

        for (word in tmpList) {
            var matchTotal = 0
            var wordUsed = string
            val wordCharArray = word.toCharArray()
            for (wordChar in wordCharArray) {
                if (doesCharacterMatchInsideWords(wordChar, wordUsed)) {
                    wordUsed = removeCharacter(wordUsed, wordChar)
                    matchTotal++
                }
                if (matchTotal == wordSize) {
                    newList.add(word)
                    matchTotal = 0
                }
            }
        }
        return newList
    }

    override fun getAvailableWordsFromWord(wordIn: String): List<String> {
        val tmpWords = mutableListOf<String>()
        for (i in 3..wordIn.length) {
            val tmpList = getWordListByWordLength(i)
            for (word in tmpList) {
                var matchCount = word.length
                var selectedWord = wordIn
                val arr = word.toCharArray().sorted().joinToString("")
                for (char in arr) {
                    if (char in selectedWord) {
                        selectedWord = selectedWord.replace("$char", "")
                        matchCount--
                    }
                }
                if (matchCount == 0) {
                    tmpWords.add(word)
                }
            }
        }
        return tmpWords
    }

    fun shuffleString(text: String): String {
        val characters = text.toCharArray()
        for (i in characters.indices) {
            val randomIndex = getRandomIntByMax(characters.size - 1)
            val temp = characters[i]
            characters[i] = characters[randomIndex]
            characters[randomIndex] = temp
        }
        return isShuffledStringAccidentallyTheWord(text, String(characters))
    }

    fun isShuffledStringAccidentallyTheWord(word: String, shuffled: String): String {
        return if (word == shuffled) {
            shuffleString(word)
        } else shuffled
    }

    override fun getWordListByWordLength(length: Int): List<String> {
        val tmpList = ArrayList<String>(length)
        val wordList = wordLoaderWord.getWordList()
        for (word in wordList) {
            if (doesWordContainThisManyChars(word, length))
                tmpList.add(word)
        }
        return tmpList
    }

    fun doesCharacterMatchInsideWords(wordChar: Char, word: String): Boolean {
        val pattern = Pattern.compile(".*[$wordChar].*")
        val matcher = pattern.matcher(word)
        return matcher.find()
    }

    fun removeCharacter(string: String, ch: Char): String {
        return string.substring(0, string.indexOf(ch)) + string.substring(string.indexOf(ch) + 1)
    }

    private fun getRandomIntByMax(max: Int): Int {
        return (0..max).random()
    }
}
