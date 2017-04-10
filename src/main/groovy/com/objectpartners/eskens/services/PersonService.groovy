package com.objectpartners.eskens.services

import com.objectpartners.eskens.repos.PersonRepo
import org.springframework.stereotype.Service

@Service
class PersonService {

    final PersonRepo personRepo

    PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo
    }

    String getAddressToForPersonId(Long personId) {
        def p = personRepo.findOne(personId)
        "$p.title $p.firstName $p.lastName"
    }
}
