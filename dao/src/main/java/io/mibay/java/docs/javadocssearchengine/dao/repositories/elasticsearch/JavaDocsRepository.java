package io.mibay.java.docs.javadocssearchengine.dao.repositories.elasticsearch;

import io.mibay.java.docs.javadocssearchengine.model.JavaDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JavaDocsRepository extends ElasticsearchRepository<JavaDoc, String> {
}
