package io.mibay.java.docs.javadocssearchengine.integration.config;

import io.mibay.java.docs.javadocssearchengine.integration.processors.FileProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.file.transformer.FileToStringTransformer;
import org.springframework.messaging.MessageChannel;

import java.io.File;

@Configuration
@EnableIntegration
@ImportResource("classpath:spring/integration-context.xml")
public class IntegrationConfig {

    @Bean
    public MessageChannel fileInputChannel() {
        return new DirectChannel();
    }

    @Bean
    @InboundChannelAdapter(value = "javaDocsDirIn", poller = @Poller(fixedDelay = "1000"))
    public MessageSource<File> fileReadingMessageSource() {
        FileReadingMessageSource source = new FileReadingMessageSource();
        source.setDirectory(new File("D:\\Users\\mibaye\\Dev\\Docs\\Java\\jdk-8u181-docs-all\\docs"));
        source.setFilter(new SimplePatternFileListFilter("*.html"));
        return source;
    }

    @Bean
    public FileToStringTransformer fileToStringTransformer() {
        return new FileToStringTransformer();
    }

    @Bean
    public FileProcessor fileProcessor() {
        return new FileProcessor();
    }

    @Bean
    public IntegrationFlow processFileFlow() {
        return IntegrationFlows.from("javaDocsDirIn")
                .transform(fileToStringTransformer())
                .handle("fileProcessor", "process")
                .get();
    }

}
