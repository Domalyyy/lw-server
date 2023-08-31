package ua.domaly.lwserver.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * Method to handle transactions on DB side.
 */
@RequiredArgsConstructor
@EnableTransactionManagement
@Configuration
public class PersistenceJPAConfig {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    /**
     * Transactions manager.
     *
     * @return instance of {@link PlatformTransactionManager}.
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        final var transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }
}
