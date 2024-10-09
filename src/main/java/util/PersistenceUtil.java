package util;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersistenceUtil {

    private static final Logger logger = LoggerFactory.getLogger(PersistenceUtil.class);
    private static EntityManagerFactory entityManagerFactory;

    static {
        try {
            Map<String, Object> properties = new HashMap<>();

            String url = System.getenv("DB_URL") != null ? System.getenv("DB_URL") : "";
            String user = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "";
            String password = System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : "";

            if (url == null || user == null || password == null || url.isEmpty() || user.isEmpty()) {
                logger.error("One or more required environment variables are missing.");
                throw new IllegalStateException("Database configuration is incomplete.");
            }

            properties.put("javax.persistence.jdbc.user", user);
            properties.put("javax.persistence.jdbc.password", password);

            String persistenceUnitName = System.getProperty("persistence.unit.name", "3CHAN_PU");

            if (persistenceUnitName.equals("test_3CHAN_PU"))
                url = "jdbc:mysql://localhost:3306/3chan_test";

            properties.put("javax.persistence.jdbc.url", url);

            logger.info("DB USER : " + user);
            logger.info("DB PASSWORD:" + password);

            entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName, properties);
        } catch (Throwable ex) {
            logger.error("Initial EntityManagerFactory creation failed.", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public static void shutdown() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}
