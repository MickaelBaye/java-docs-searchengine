package io.mibay.java.docs.javadocssearchengine.business;

import io.mibay.java.docs.javadocssearchengine.dao.JavaDocsDAO;
import io.mibay.java.docs.javadocssearchengine.model.JavaDoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JavaDocsBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(JavaDocsBusiness.class);

    @Autowired
    private JavaDocsDAO javaDocsDAO;

    public JavaDoc save(JavaDoc entity) throws Exception {
        JavaDoc javaDoc;
        if (entity == null) {
            throw new IllegalArgumentException("Can't save null object");
        }
        try {
            LOGGER.debug("Saving: {}", entity.toString());
            javaDoc = javaDocsDAO.save(entity);
        } catch (Exception e) {
            throw new Exception("Error on java document save", e);
        }
        return javaDoc;
    }

    public Optional<JavaDoc> findById(String id) throws Exception {
        Optional<JavaDoc> javaDoc;
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID is null or empty");
        }
        try {
            LOGGER.debug("Get by ID: {}", id);
            javaDoc = javaDocsDAO.findById(id);
        } catch (Exception e) {
            throw new Exception("Error on java documentation find", e);
        }
        return javaDoc;
    }

    public Iterable<JavaDoc> findAll() {
        return javaDocsDAO.findAll();
    }

    public void delete(JavaDoc entity) throws Exception {
        Optional<JavaDoc> javaDoc;
        if (entity == null) {
            throw new IllegalArgumentException("Can't delete null object");
        }
        try {
            LOGGER.debug("Delete: {}", entity);
            javaDocsDAO.delete(entity);
        } catch (Exception e) {
            throw new Exception("Error on java documentation find", e);
        }
    }
}
