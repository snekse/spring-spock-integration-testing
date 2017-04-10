package com.objectpartners.eskens.services

import com.blogspot.toomuchcoding.spock.subjcollabs.Collaborator
import com.blogspot.toomuchcoding.spock.subjcollabs.Subject
import com.objectpartners.eskens.entities.Person
import com.objectpartners.eskens.repos.PersonRepo
import spock.lang.Specification

/**
 * A basic unit test w/o Spring components
 * Created by derek on 4/10/17.
 */
class PersonServiceTest extends Specification {

    @Subject PersonService personService

    @Collaborator PersonRepo personRepo = Mock()

    def "GetAddressToForPersonId"() {
        when:
        def addressTo = personService.getAddressToForPersonId(1L)

        then: 'Return an expected Person from our mocked repo'
        1 * personRepo.findOne(1L) >> { Long id ->
            new Person(id: id, firstName: 'James', lastName: 'Kirk', title: 'Capt')
        }

        and: 'AddressTo is formatted as expected'
        addressTo == 'Capt James Kirk'
    }
}
