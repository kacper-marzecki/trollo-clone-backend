package com.kmarzecki.trollo.repository

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

/**
 * Repository configuration
 */
@Configuration
@EnableTransactionManagement
class RepositoryConfiguration(
        private val dataSource: DataSource
) {

    /**
     * @return TransactionManager bean
     */
    @Bean
    fun transactionManager(): DataSourceTransactionManager {
        return DataSourceTransactionManager(dataSource)
    }
}