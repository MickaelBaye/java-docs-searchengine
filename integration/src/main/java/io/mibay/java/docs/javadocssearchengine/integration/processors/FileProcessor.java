package io.mibay.java.docs.javadocssearchengine.integration.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

public class FileProcessor {

    private Logger LOGGER = LoggerFactory.getLogger(FileProcessor.class);

    private static final String HEADER_FILE_NAME = "file_name";
    private static final String MSG = "%s received. Content: %s";

    public void process(Message<String> msg) {
        String fileName = (String) msg.getHeaders().get(HEADER_FILE_NAME);
        String content = msg.getPayload();

        LOGGER.info(String.format(MSG, fileName, content));
    }

}
