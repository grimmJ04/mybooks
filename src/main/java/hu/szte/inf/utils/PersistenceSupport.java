package hu.szte.inf.utils;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PersistenceSupport {

    public static EntityManagerFactory createEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("global");
    }
}
