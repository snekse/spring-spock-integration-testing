package com.objectpartners.eskens.repos

import com.objectpartners.eskens.entities.Person
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepo extends JpaRepository<Person, Long> {
}
