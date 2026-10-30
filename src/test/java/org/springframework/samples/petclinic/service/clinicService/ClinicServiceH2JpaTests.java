package org.springframework.samples.petclinic.service.clinicService;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

/**
 * <p> Integration test using the JPA and H2 profiles.
 *
 * @see AbstractClinicServiceTests AbstractClinicServiceTests for more details. </p>
 */
@SpringBootTest
@ActiveProfiles({"h2", "jpa"})
@TestPropertySource(properties = {
    "spring.sql.init.platform=h2",
    "spring.h2.console.enabled=false",
    "spring.datasource.url=jdbc:h2:mem:petclinic-jpa;DB_CLOSE_DELAY=-1"
})
class ClinicServiceH2JpaTests extends AbstractClinicServiceTests {

    @Autowired
    EntityManager entityManager;

    @Override
    void clearCache() {
        entityManager.clear();
    }
}
