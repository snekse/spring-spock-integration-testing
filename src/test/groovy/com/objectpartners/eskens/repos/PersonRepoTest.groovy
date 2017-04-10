package com.objectpartners.eskens.repos

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import spock.lang.Specification

/**
 * Smoke tests to make sure repo works as expected
 * Created by derek on 4/10/17.
 */
@DataJpaTest
class PersonRepoTest extends Specification {

    @Autowired PersonRepo personRepo

    void "can select all from Person without blowing up"() {

        when: 'doing a standard JPA select all from table'
        def result = personRepo.findAll()

        then: 'we do not blow up'
        noExceptionThrown()

        then: 'we find exactly 1 result from our seed data'
        result.size() == 1
    }

    void 'can search by last name'() {

        when: 'last name is expected in database start up script'
        def persons = personRepo.findByLastNameStartingWith('Kir')

        then: 'we find just capt james kirk'
        persons.size() == 1
        persons.first().firstName == 'James'
    }

}