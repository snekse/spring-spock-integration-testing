package com.opi.eskens.services;

import com.opi.eskens.entities.Person;
import com.opi.eskens.repos.PersonRepo;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    final PersonRepo personRepo;

    final ExternalRankingService externalRankingService;

    public PersonService(PersonRepo pr, ExternalRankingService ers) {
        this.personRepo = pr;
        this.externalRankingService = ers;
    }

    public String getAddressToForPersonId(Long personId) {
        Person p = getPerson(personId);
        return String.join(" ", p.getTitle(), p.getFirstName(), p.getLastName());
    }

    public Rank getRank(Long personId) {
        return externalRankingService.getRank(getPerson(personId));
    }

    private Person getPerson(Long personId) {
        return personRepo.findOne(personId);
    }
}
