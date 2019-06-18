package tech.klopper.anagramus.service.io

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.lang.Nullable
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import tech.klopper.anagramus.model.WordModel
import java.io.File
import java.io.FileNotFoundException

@Service
@Configuration
class DictionaryLoader(
        @Value("\${tech.klopper.anagramus.dictionary.path}") private val dictListPath: String,
        @Value("\${tech.klopper.anagramus.dictionary.definition.path}") private val dictDefinePath: String
) : Dictionary {

    private var wordList: MutableList<WordModel> = mutableListOf()

    init {
        try {
            val wordListTmp = File(dictListPath).useLines { it.toList() }
            for (word in wordListTmp) {
                wordList.add(WordModel(word))
            }
        } catch (e: FileNotFoundException) {
            //TODO Handle exception
            e.printStackTrace()
        }
    }

    override fun getWordList(): List<WordModel> {
        return wordList
    }

    override fun <T> getForObject(word: String, responseType: Class<T>, vararg uriVariables: Any): String? {
        return RestTemplate().getForObject<String>("$dictDefinePath$word", String::class.java)
    }
}

interface Dictionary {
    fun getWordList(): List<WordModel>
    @Nullable
    @Throws(RestClientException::class)
    fun <T> getForObject(word: String, responseType: Class<T>, vararg uriVariables: Any): String?
}
