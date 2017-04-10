package com.objectpartners.eskens.controllers

import com.objectpartners.eskens.entities.Person
import com.objectpartners.eskens.services.ExternalRankingService
import com.objectpartners.eskens.services.Rank
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import spock.lang.Specification
import spock.mock.DetachedMockFactory

import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * An integration test illustrating how to wire everything w/ Spring,
 * but replace certain components with Spock mocks
 * Created by derek on 4/10/17.
 */
@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerIntTest extends Specification {

    @Autowired MockMvc mvc

    /**
     * This is our mock we created below. We inject it in so we can control it in our specs.
     */
    @Autowired ExternalRankingService externalRankingServiceMock

    def "GetRank"() {
        when: 'Calling getRank for a known seed data entity'
        MvcResult mvcResult = mvc.perform(get("/persons/1/rank").contentType(APPLICATION_JSON))
                                .andExpect(status().is2xxSuccessful()).andReturn()

        then: 'we define the mock for JUST the external service'
        externalRankingServiceMock.getRank({ Person p -> p.id == 1L } as Person) >> {
            new Rank(level: 1, classification: 'Captain')
        }
        noExceptionThrown()

        when: 'inspecting the contents'
        def resultingJson = mvcResult.response.contentAsString

        then:
        resultingJson == 'Capt James Kirk ~ Captain:Level 1'
    }

    /**
     * This is the key.  The new DetachedMockFactory allows us to create Mocks outside the Spec.
     * That, combined with TestConfiguration getting priority for bean selection,
     * means we can define new beans here using Mock objects. We can then inject these mocks into the spec.
     */
    @TestConfiguration
    static class Config {
        private DetachedMockFactory factory = new DetachedMockFactory()

        @Bean
        ExternalRankingService externalRankingService() {
            factory.Mock(ExternalRankingService)
        }
    }
}
