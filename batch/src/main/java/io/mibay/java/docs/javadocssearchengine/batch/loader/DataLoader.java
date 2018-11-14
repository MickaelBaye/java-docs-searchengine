package io.mibay.java.docs.javadocssearchengine.batch.loader;

import io.mibay.java.docs.javadocssearchengine.dao.JavaDocsDAO;
import io.mibay.java.docs.javadocssearchengine.model.JavaDoc;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Iterator;
import java.util.UUID;

/**
 * TODO documentation
 */
@Component
public class DataLoader implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataLoader.class);
    private static final String JAVA_DOCS_DIR = "D:\\Users\\mibaye\\Dev\\Docs\\Java\\jdk-8u181-docs-all";

    @Autowired
    private JavaDocsDAO javaDocsDAO;

    /**
     * TODO documentation
     */
    public void afterPropertiesSet() {
        LOGGER.debug("DataLoader.afterPropertiesSet()");
        initializeData();
    }

    /**
     * TODO documentation
     */
    private void initializeData() {
        LOGGER.debug("Start initializing data...");

        Integer count = 0;
        Iterator<File> fileIterator = FileUtils.iterateFilesAndDirs(new File(JAVA_DOCS_DIR), new SuffixFileFilter(".html"), TrueFileFilter.TRUE);

        while (fileIterator.hasNext()) {

            File file = fileIterator.next();
            JavaDoc javaDoc = new JavaDoc();
            String filePath = file.getAbsolutePath();
            String title = file.getName();

            javaDoc.setId(UUID.randomUUID().toString());
            javaDoc.setPath(filePath);
            javaDoc.setTitle(title);

            javaDocsDAO.save(javaDoc);

            LOGGER.debug(" === {}", javaDoc.toString());
            count++;
        }

        LOGGER.debug("{} pages", count);
        LOGGER.debug("End initializing data !");
    }
}
