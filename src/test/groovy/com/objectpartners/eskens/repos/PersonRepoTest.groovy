package com.objectpartners.eskens.repos

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import spock.lang.Specification;

/**
 * Smoke tests to make sure repo works as expected
 * Created by derek on 4/10/17.
 */
@DataJpaTest
class PersonRepoTest extends Specification {

    @Autowired PersonRepo personRepo

    void "can select all from Person without blowing up"() {
        when: 'doing a standard JPA select I from table'
        def result = personRepo.findAll()

        then:
        noExceptionThrown()

        then:
        result.size() == 0
    }

}