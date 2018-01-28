package com.opi.eskens.services;

import com.opi.eskens.entities.Person;
import com.opi.eskens.repos.PersonRepo;
import extensions.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * An example of what @{link {@link PersonServiceSpockTest} would look like
 * as a Java JUnit test
 */
@ExtendWith(MockitoExtension.class)
class PersonServiceJavaJUnitTest {

    @InjectMocks
    PersonService personService;

    @Mock
    PersonRepo personRepo;

    @Mock
    ExternalRankingService externalRankingService;

    Person jamesKirk = new Person();
    {
        jamesKirk.setId(1L);
        jamesKirk.setFirstName("James");
        jamesKirk.setLastName("Kirk");
        jamesKirk.setTitle("Capt");
    }

    @Test
    void testGetAddressToForPersonId() {
        // 'Return an expected Person from our mocked repo'
        when(personRepo.findOne(eq(1L))).thenReturn(jamesKirk);

        String addressTo = personService.getAddressToForPersonId(1L);

        verify(personRepo).findOne(1L);

        // 'AddressTo is formatted as expected'
        assertEquals("Capt James Kirk", addressTo);
    }

    @Test
    void testGetRankByPersonId() {
        Rank newRank = new Rank();
        newRank.setLevel(1);
        newRank.setClassification("Captain");

        // 'Return an expected Rank from our mocked rank service'
        when(personRepo.findOne(eq(1L))).thenReturn(jamesKirk);
        when(externalRankingService.getRank(eq(jamesKirk))).thenReturn(newRank);

        Rank rank = personService.getRank(1L);

        verify(personRepo).findOne(1L);
        verify(externalRankingService).getRank(jamesKirk);

        // 'we return the Rank from the external service unaltered'
        assertEquals(1L, rank.getLevel());
        assertEquals("Captain", rank.getClassification());
    }
}
