package io.mibay.java.docs.javadocssearchengine.business;

import io.mibay.java.docs.javadocssearchengine.dao.JavaDocsDAO;
import io.mibay.java.docs.javadocssearchengine.model.JavaDoc;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JavaDocsBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(JavaDocsBusiness.class);

    @Autowired
    private JavaDocsDAO javaDocsDAO;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    /**
     * TODO documentation
     * @param entity
     * @return
     * @throws Exception
     */
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

    /**
     * TODO documentation
     * @param id
     * @return
     * @throws Exception
     */
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

    /**
     * TODO documentation
     * @return
     */
    public Iterable<JavaDoc> findAll() {
        return javaDocsDAO.findAll();
    }

    /**
     * TODO documentation
     * @param entity
     * @throws Exception
     */
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

    /**
     * TODO documentation
     * @param title
     * @return
     * @throws Exception
     */
    public List<JavaDoc> searchByTitle(String title) throws Exception {
        List<JavaDoc> javaDocList = null;
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Can't search with empty or null title");
        }
        try {
            SearchQuery searchQuery = new NativeSearchQueryBuilder()
                    .withQuery(new MatchQueryBuilder("title", title))
                    .build();
            return elasticsearchOperations.queryForList(searchQuery, JavaDoc.class);
        } catch (Exception e) {
            throw new Exception("Error on java documentation search by title", e);
        }
    }
}
