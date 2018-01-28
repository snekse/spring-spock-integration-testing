package com.opi.eskens.entities

import groovy.transform.Canonical

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
@Canonical
class Person {

    @Id @GeneratedValue
    Long id

    String firstName

    String lastName

    String title
}
