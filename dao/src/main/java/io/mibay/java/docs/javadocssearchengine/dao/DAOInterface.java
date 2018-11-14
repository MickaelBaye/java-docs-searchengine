package io.mibay.java.docs.javadocssearchengine.dao;

import java.util.Optional;

public interface DAOInterface<T> {

    T save(T entity);

    Optional<T> findById(String id);

    Iterable<T> findAll();

    void delete(T entity);
}
