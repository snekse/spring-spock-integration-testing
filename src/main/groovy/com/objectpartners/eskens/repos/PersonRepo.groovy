package com.objectpartners.eskens.repos

import com.objectpartners.eskens.entities.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepo extends JpaRepository<Person, Long> {

    List<Person> findByLastNameStartingWith(String lastName)
}
