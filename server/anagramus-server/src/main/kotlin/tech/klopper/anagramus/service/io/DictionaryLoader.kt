package tech.klopper.anagramus.service.io

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.lang.Nullable
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import tech.klopper.word.io.WordResourceLoader
import java.io.File
import java.io.FileNotFoundException

@Service
@Configuration
class DictionaryLoader(
    @Value("\${tech.klopper.anagramus.dictionary.path}") private val dictListPath: String,
    @Value("\${tech.klopper.anagramus.dictionary.definition.path}") private val dictDefinePath: String
) : Dictionary {

    private var wordList: MutableList<String> = mutableListOf()

    init {
        try {
            val wordListTmp = File(dictListPath).useLines { it.toList() }
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

    override fun <T> getForObject(word: String, responseType: Class<T>, vararg uriVariables: Any): String? {
        return RestTemplate().getForObject("$dictDefinePath$word", String::class.java)
    }
}

interface Dictionary : WordResourceLoader {
    @Nullable
    @Throws(RestClientException::class)
    fun <T> getForObject(word: String, responseType: Class<T>, vararg uriVariables: Any): String?
}
