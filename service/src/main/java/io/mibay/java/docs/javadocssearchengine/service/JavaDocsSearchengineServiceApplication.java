package io.mibay.java.docs.javadocssearchengine.service;

import io.mibay.java.docs.javadocssearchengine.batch.loader.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class JavaDocsSearchengineServiceApplication {

    @Autowired
    private DataLoader dataLoader;

    public static void main(String[] args) {
        SpringApplication.run(JavaDocsSearchengineServiceApplication.class, args);
    }
}
