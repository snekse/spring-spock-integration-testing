package com.opi.eskens.services

import com.blogspot.toomuchcoding.spock.subjcollabs.Collaborator
import com.blogspot.toomuchcoding.spock.subjcollabs.Subject
import com.opi.eskens.entities.Person
import com.opi.eskens.repos.PersonRepo
import spock.lang.Specification

/**
 * A basic unit test w/o Spring components
 * Created by derek on 4/10/17.
 */
class PersonServiceSpockTest extends Specification {

    @Subject PersonService personService

    @Collaborator PersonRepo personRepo = Mock()

    @Collaborator ExternalRankingService externalRankingService = Mock()

    Person jamesKirk = new Person(id: 1L, firstName: 'James', lastName: 'Kirk', title: 'Capt')

    def "GetAddressToForPersonId"() {
        when:
        def addressTo = personService.getAddressToForPersonId(1L)

        then: 'Return an expected Person from our mocked repo'
        1 * personRepo.findOne(1L) >>  { jamesKirk }

        and: 'AddressTo is formatted as expected'
        addressTo == 'Capt James Kirk'
    }

    def "GetRank by personId"() {
        when:
        def rank = personService.getRank(1L)

        then: 'Return an expected Rank from our mocked rank service'
        1 * personRepo.findOne(1L) >> { jamesKirk }
        1 * externalRankingService.getRank(jamesKirk) >> {
            new Rank(level: 1, classification: 'Captain')
        }

        and: 'we return the Rank from the external service unaltered'
        rank.level == 1
        rank.classification == 'Captain'
    }
}
