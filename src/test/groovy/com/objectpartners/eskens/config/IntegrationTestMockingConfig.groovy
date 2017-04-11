package com.objectpartners.eskens.config

import com.objectpartners.eskens.services.ExternalRankingService
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import spock.mock.DetachedMockFactory

/**
 * This is the key.  The new DetachedMockFactory allows us to create Mocks outside the Spec.
 * That, combined with TestConfiguration getting priority for bean selection,
 * means we can define new beans here using Mock objects. We can then inject these mocks into the spec.
 */
@TestConfiguration
class IntegrationTestMockingConfig {
    private DetachedMockFactory factory = new DetachedMockFactory()

    @Bean
    ExternalRankingService externalRankingService() {
        factory.Mock(ExternalRankingService)
    }
}
