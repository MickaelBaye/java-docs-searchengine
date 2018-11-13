package io.mibay.java.docs.javadocssearchengine.batch.loader;

import io.mibay.java.docs.javadocssearchengine.model.JavaDoc;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Iterator;

/**
 * TODO documentation
 */
@Component
public class DataLoader implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataLoader.class);

    private static final String JAVA_DOCS_DIR = "D:\\Users\\mibaye\\Dev\\Docs\\Java\\jdk-8u181-docs-all";

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

        Iterator<File> fileIterator = FileUtils.iterateFilesAndDirs(new File(JAVA_DOCS_DIR), new SuffixFileFilter(".html"), TrueFileFilter.TRUE);
        while (fileIterator.hasNext()) {
            File file = fileIterator.next();
            // LOGGER.debug("=== {}", file.getAbsolutePath());

            JavaDoc javaDoc = new JavaDoc();
            String filePath = file.getAbsolutePath();
            String title = file.getName();
            javaDoc.setPath(filePath);
            javaDoc.setTitle(title);

            LOGGER.debug(" === {}", javaDoc.toString());
        }

        LOGGER.debug("End initializing data !");
    }
}
