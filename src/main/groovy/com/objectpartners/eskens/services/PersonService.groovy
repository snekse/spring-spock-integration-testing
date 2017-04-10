package com.objectpartners.eskens.services

import com.objectpartners.eskens.entities.Person
import com.objectpartners.eskens.repos.PersonRepo
import org.springframework.stereotype.Service

@Service
class PersonService {

    final PersonRepo personRepo

    final ExternalRankingService externalRankingService

    PersonService(PersonRepo pr, ExternalRankingService ers) {
        this.personRepo = pr
        this.externalRankingService = ers
    }

    String getAddressToForPersonId(Long personId) {
        def p = getPerson(personId)
        "$p.title $p.firstName $p.lastName"
    }

    Rank getRank(Long personId) {
        externalRankingService.getRank(getPerson(personId))
    }

    private Person getPerson(Long personId) {
        personRepo.findOne(personId)
    }
}
