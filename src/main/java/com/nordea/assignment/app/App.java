package com.nordea.assignment.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@EnableAutoConfiguration
@SpringBootApplication
public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main( String[] args ) throws IOException {
        ApplicationContext ctx =  new SpringApplicationBuilder().sources(App.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
        AppRunner runner = (AppRunner) ctx.getBean("appRunner");

        LOG.debug("starting spring context with spring-boot");
        runner.runApplication(args);
    }
}
