package com.opi.eskens.services

import com.opi.eskens.entities.Person
import com.opi.eskens.repos.PersonRepo
import extensions.MockitoExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.mockito.ArgumentMatchers.eq
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

/**
 * An example of why Spock _is_ Groovy > than JUnit _in_ Groovy
 * This spec ends up sharing more in common w/ {@link PersonServiceJavaJUnitTest}
 * than {@link PersonServiceSpockTest}
 */
@ExtendWith(MockitoExtension.class)
class PersonServiceGroovyJUnitTest {

    @InjectMocks
    PersonService personService

    @Mock
    PersonRepo personRepo

    @Mock
    ExternalRankingService externalRankingService

    Person jamesKirk = new Person(id: 1, firstName: 'James', lastName: 'Kirk', title: 'Capt')

    @Test
    void testGetAddressToForPersonId() {
        // 'Return an expected Person from our mocked repo'
        when(personRepo.findOne(eq(1L))).thenReturn(jamesKirk)

        String addressTo = personService.getAddressToForPersonId(1L)

        verify(personRepo).findOne(1L)

        // 'AddressTo is formatted as expected'
        assertEquals("Capt James Kirk", addressTo)
    }

    @Test
    void testGetRankByPersonId() {
        // 'Return an expected Rank from our mocked rank service'
        when(personRepo.findOne(eq(1L))).thenReturn(jamesKirk)
        when(externalRankingService.getRank(eq(jamesKirk)))
                .thenReturn(new Rank(level: 1, classification: 'Captain') )

        Rank rank = personService.getRank(1L)

        verify(personRepo).findOne(1L)
        verify(externalRankingService).getRank(jamesKirk)

        // 'we return the Rank from the external service unaltered' - You can use
        assert 1 == rank.getLevel()
        assert "Captain" == rank.getClassification()
    }
}
