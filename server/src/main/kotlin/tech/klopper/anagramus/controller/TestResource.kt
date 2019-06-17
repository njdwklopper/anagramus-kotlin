package tech.klopper.anagramus.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import tech.klopper.anagramus.model.TestJson

@RestController
class TestResource {

    @RequestMapping(value = ["/test"], method = [RequestMethod.GET])
    fun apiGetTests(): List<TestJson> {
        val tasks = mutableListOf<TestJson>()
        for (i in 1..10) {
            val tmpT = TestJson()
            tmpT.name = "name$i"
            tmpT.outId = i.toLong()
            tasks += tmpT
        }
        return tasks
    }
}
