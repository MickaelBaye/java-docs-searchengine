package io.mibay.java.docs.javadocssearchengine.dao;

import io.mibay.java.docs.javadocssearchengine.model.JavaDoc;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JavaDocsDAO implements DAOInterface<JavaDoc> {

    private List<JavaDoc> javaDocList = new ArrayList<>();

    @Override
    public JavaDoc save(JavaDoc entity) {
        javaDocList.add(entity);
        return entity;
    }

    @Override
    public Optional<JavaDoc> findById(String id) {
        return javaDocList.stream().filter(javaDoc -> javaDoc.getId().equals(id))
                .findFirst();
    }

    @Override
    public Iterable<JavaDoc> findAll() {
        return javaDocList;
    }

    @Override
    public void delete(JavaDoc entity) {
        javaDocList.remove(entity);
    }
}
