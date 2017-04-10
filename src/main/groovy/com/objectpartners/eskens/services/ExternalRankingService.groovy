package com.objectpartners.eskens.services

import com.objectpartners.eskens.entities.Person
import org.springframework.stereotype.Service

/**
 * This is to mimic calls to an external 3rd party service that you wouldn't want to test locally.
 * Created by derek on 4/10/17.
 */
@Service
class ExternalRankingService {

    @SuppressWarnings("GrMethodMayBeStatic")
    Rank getRank(Person person) {
        throw new RuntimeException('This feature is not yet implemented.')
    }
}

class Rank {
    int level
    String classification
}
