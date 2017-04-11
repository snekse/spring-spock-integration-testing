**How to inject Spock mocks into Spring integration tests**

This project is intended to be used as en example guide to illustrate how you can use Spock with Spring (and Spring Boot) with a mix of Spring configuration and Spock mocks.

Sometimes you want to do fuller integration tests with your entire Spring context in place. This usually leads to those handful of problem spots that you'd rather just mock.

In Spring Boot 1.4, they introduced a lot of cool testing toys, including `@MockBean`. Sadly that's only supported when testing with JUnit.

Thankfully Spock 1.1 introduced the `DetachedMockFactory`. This, combined with the new Spring Boot `@TestConfiguration` means you can achieve a level up in your testing happiness factor.

The heart of this example lives in our `PersonControllerIntTest`.  The `PersonControllerIntTest` spins up a Spring context so we can make a `MockMvc` call to a REST endpoint which pulls data from an h2 database via a Spring Data repo, but the "Rank" data we would normally get from an external service has been mocked.