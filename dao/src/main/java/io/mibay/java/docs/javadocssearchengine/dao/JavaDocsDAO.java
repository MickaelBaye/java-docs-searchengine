package io.mibay.java.docs.javadocssearchengine.dao;

import io.mibay.java.docs.javadocssearchengine.dao.repositories.elasticsearch.JavaDocsRepository;
import io.mibay.java.docs.javadocssearchengine.model.JavaDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JavaDocsDAO implements DAOInterface<JavaDoc> {

    @Autowired
    private JavaDocsRepository javaDocsRepository;

    @Override
    public JavaDoc save(JavaDoc entity) {
        return javaDocsRepository.save(entity);
    }

    @Override
    public Optional<JavaDoc> findById(String id) {
        return javaDocsRepository.findById(id);
    }

    @Override
    public Iterable<JavaDoc> findAll() {
        return javaDocsRepository.findAll();
    }

    @Override
    public void delete(JavaDoc entity) {
        javaDocsRepository.delete(entity);
    }
}
