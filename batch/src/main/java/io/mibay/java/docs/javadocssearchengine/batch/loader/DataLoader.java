package io.mibay.java.docs.javadocssearchengine.batch.loader;

import io.mibay.java.docs.javadocssearchengine.batch.filters.CustomDirectoryFilter;
import io.mibay.java.docs.javadocssearchengine.batch.filters.CustomFileFilter;
import io.mibay.java.docs.javadocssearchengine.business.JavaDocsBusiness;
import io.mibay.java.docs.javadocssearchengine.model.JavaDoc;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Collection;
import java.util.UUID;

/**
 * TODO documentation
 */
@Component
public class DataLoader implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataLoader.class);
    // private static final String JAVA_DOCS_DIR = "D:\\Users\\mibaye\\Dev\\Docs\\Java\\jdk-8u181-docs-all";
    private static final String JAVA_DOCS_DIR = "D:\\Users\\mibaye\\Dev\\Docs\\Java\\jdk-8u181-docs-all\\docs\\api\\java\\applet";

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    private JavaDocsBusiness javaDocsBusiness;

    /**
     * TODO documentation
     * @throws Exception
     */
    public void afterPropertiesSet() throws Exception {
        LOGGER.debug("DataLoader.afterPropertiesSet()");
        initializeIndex();
        initializeData();
    }

    /**
     * TODO documentation
     * @throws Exception
     */
    private void initializeIndex() throws Exception {
        elasticsearchOperations.createIndex(JavaDoc.class);
    }

    /**
     * TODO documentation
     * @throws Exception
     */
    private void initializeData() throws Exception {
        LOGGER.debug("Start initializing data...");

        Integer count = 0;
        // Iterator<File> fileIterator = FileUtils.iterateFilesAndDirs(new File(JAVA_DOCS_DIR), new SuffixFileFilter(".html"), TrueFileFilter.TRUE);
        Collection<File> files = FileUtils.listFilesAndDirs(new File(JAVA_DOCS_DIR), new CustomFileFilter(), new CustomDirectoryFilter());

        for (File file : files) {

            if (file.isFile()) {
                JavaDoc javaDoc = new JavaDoc();
                String filePath = file.getAbsolutePath();
                String title;

                try {
                    Document doc = Jsoup.parse(file, "UTF-8", "https://docs.oracle.com/javase/8/docs/api/");
                    Element t = doc.body().selectFirst("h2");
                    title = t.text();

                    javaDoc.setId(UUID.randomUUID().toString());
                    javaDoc.setPath(filePath);
                    javaDoc.setTitle(title);

                    javaDoc = javaDocsBusiness.save(javaDoc);

                    count++;
                } catch (Exception e) {
                    LOGGER.error("Failed to parse: '{}'", filePath);
                    LOGGER.debug("Exception:", e);
                }
            }
        }

        LOGGER.debug("{} pages", count);
        LOGGER.debug("End initializing data !");
    }
}
