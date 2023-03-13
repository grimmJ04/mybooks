package hu.szte.inf.utils;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Helper class for JPA persistence.
 * Refer to config file under: resources/META-INF/persistence.xml
 */
public class PersistenceSupport {

    private static EntityManagerFactory entityManagerFactoryInstance;
    private static String name = "";

    public static EntityManagerFactory getInstance(String persistenceUnitName) {
        if (entityManagerFactoryInstance == null || !entityManagerFactoryInstance.isOpen()) {
            entityManagerFactoryInstance = PersistenceSupport.createEntityManagerFactory(persistenceUnitName);
        }
        else if (!name.equals(persistenceUnitName) && entityManagerFactoryInstance.isOpen()) {
            entityManagerFactoryInstance.close();
            entityManagerFactoryInstance = PersistenceSupport.createEntityManagerFactory(persistenceUnitName);
        }
        name = persistenceUnitName;
        return entityManagerFactoryInstance;
    }

    /**
     * Creates the default entity manager factory.
     * In this context, every CrudRepository will have its own factory.
     * If used outside CrudRepositories, close should be called on a specific instance of EntityManagerFactory.
     *
     * @param persistenceUnitName The name of the persistence unit configuration,
     *                            found in persistence.xml. A unit with the specified
     *                            name will be instantiated.
     * @return EntityManagerFactory with configuration corresponding to a specific name.
     */
    public static EntityManagerFactory createEntityManagerFactory(String persistenceUnitName) {
        return Persistence.createEntityManagerFactory(persistenceUnitName);
    }

    /**
     * Creates the default entity manager factory.
     * In this context, every CrudRepository will have its own factory.
     * If used outside CrudRepositories, close should be called on a specific instance of EntityManagerFactory.
     *
     * @return EntityManagerFactory used to instantiate entity managers.
     */
    public static EntityManagerFactory createEntityManagerFactory() {
        return createEntityManagerFactory("global");
    }
}
